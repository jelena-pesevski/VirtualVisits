package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.VirtualVisit;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;
import org.unibl.etf.virtualvisits.models.requests.BuyTicketRequest;
import org.unibl.etf.virtualvisits.models.requests.TransactionRequest;
import org.unibl.etf.virtualvisits.repositories.TicketEntityRepository;
import org.unibl.etf.virtualvisits.services.*;
import org.unibl.etf.virtualvisits.utils.MailSenderRunnable;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketEntityRepository repository;

    private final VirtualVisitService virtualVisitService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    private final MailService mailService;

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private final LogService logService;

    @Value("${virtual-bank.url}")
    private String bankUrl;

    public TicketServiceImpl(TicketEntityRepository repository, VirtualVisitService virtualVisitService, UserService userService, ModelMapper modelMapper, RestTemplate restTemplate, MailService mailService, ThreadPoolTaskScheduler threadPoolTaskScheduler, LogService logService) {
        this.repository = repository;
        this.virtualVisitService = virtualVisitService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.mailService = mailService;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        this.logService = logService;
    }

    @Override
    public void buyTicket(BuyTicketRequest request) throws NotFoundException{
        logService.insert(new LogEntity(0, "Buy ticket try for user id:"+ request.getUserId()+ " and visit "+ request.getVirtualVisitId(), "BUY-TICKET-TRY", Instant.now()));

        User user=userService.findById(request.getUserId());
        VirtualVisit virtualVisit=virtualVisitService.findById(request.getVirtualVisitId());

        //setting real value just in case request is malicious
        request.setCashAmount(virtualVisit.getPrice());

        //contact bank
        TransactionRequest transactionRequest=modelMapper.map(request, TransactionRequest.class);
        boolean bankResponse=contactBank(transactionRequest);

        if(!bankResponse){
            logService.insert(new LogEntity(0, "Transaction failed for user:"+user.getUsername()+" buying ticket for virtual visit:"+ virtualVisit.getVirtualVisitId() , "TRANSACTION-FAILED", Instant.now()));
            throw new NotFoundException();
        }

        //insert ticket into database
        TicketEntity ticketEntity=modelMapper.map(request, TicketEntity.class);
        ticketEntity.setTicketId(null);
        ticketEntity.setTicketNumber(generateTicketNumber());
        repository.save(ticketEntity);

        //send mail
        try{
            mailService.sendTicket(ticketEntity, user);
        }catch (Exception e){
            logService.insert(new LogEntity(0, "Ticket couldn't be sent to "+ user.getUsername()+ " for visit "+ virtualVisit.getVirtualVisitId(), "SEND-TICKET-FAIL", Instant.now()));
           System.out.println("Ticket is not sent");
        }

        //get Instant from date and start time
        //then schedule task
        LocalDate localDate = virtualVisit.getDate().toLocalDate();
        LocalTime localTime=virtualVisit.getStart().toLocalTime();
        Instant instant= getInstant(localDate, localTime);

        threadPoolTaskScheduler.schedule(new MailSenderRunnable(mailService, user.getMail(), "Virtual visit at "+ virtualVisit.getMuseumName()+" starts in an hour."), Date.from(instant.minus(1, ChronoUnit.HOURS)));

        //get Instant from date and ending time, and then schedule task
        LocalTime durationTime=virtualVisit.getDuration().toLocalTime();
        localTime=localTime.plus(Duration.ofNanos(durationTime.toNanoOfDay()));
        instant= getInstant(localDate, localTime);

        threadPoolTaskScheduler.schedule(new MailSenderRunnable(mailService, user.getMail(), "Virtual visit at "+virtualVisit.getMuseumName()+ " ends in 5 minutes."), Date.from(instant.minus(5, ChronoUnit.MINUTES)));

        logService.insert(new LogEntity(0,  user.getUsername()+" bought ticket for visit "+ virtualVisit.getVirtualVisitId() + " . Ticket number:"+ ticketEntity.getTicketNumber(), "BUY-TICKET", Instant.now()));
    }

    @Override
    public TicketEntity findByVirtualVisitIdAndUserId(Integer virtualVisitId, Integer userId) throws NotFoundException {
        return repository.findByVirtualVisitIdAndUserId(virtualVisitId, userId).orElseThrow(NotFoundException::new);
    }

    private boolean contactBank(TransactionRequest transactionRequest) {
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransactionRequest> entity=new HttpEntity<>(transactionRequest, headers);

        try{
            ResponseEntity<String> response =this.restTemplate.postForEntity(bankUrl, transactionRequest, String.class);

            //check response status
            if (response.getStatusCode() == HttpStatus.OK) {
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    private String generateTicketNumber(){
        return String.valueOf(System.currentTimeMillis());
    }

    private Instant getInstant(LocalDate localDate, LocalTime localTime){
        LocalDateTime ldt=localDate.atTime(localTime);
        return ZonedDateTime.of(ldt, ZoneId.systemDefault()).toInstant();
    }
}
