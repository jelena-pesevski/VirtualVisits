package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Ticket;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.VirtualVisit;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;
import org.unibl.etf.virtualvisits.models.requests.BuyTicketRequest;
import org.unibl.etf.virtualvisits.models.requests.TransactionRequest;
import org.unibl.etf.virtualvisits.repositories.TicketEntityRepository;
import org.unibl.etf.virtualvisits.services.TicketService;
import org.unibl.etf.virtualvisits.services.UserService;
import org.unibl.etf.virtualvisits.services.VirtualVisitService;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import java.util.Random;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketEntityRepository repository;

    private final UserService userService;

    private final VirtualVisitService virtualVisitService;

    private final ModelMapper modelMapper;

    private final JavaMailSender mailSender;

    private final RestTemplate restTemplate;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${mail.subject}")
    private String subject;

    @Value("${mail.text}")
    private String text;

    @Value("${virtual-bank.url}")
    private String bankUrl;

    public TicketServiceImpl(TicketEntityRepository repository, UserService userService, VirtualVisitService virtualVisitService, ModelMapper modelMapper, JavaMailSender mailSender, RestTemplate restTemplate) {
        this.repository = repository;
        this.userService = userService;
        this.virtualVisitService = virtualVisitService;
        this.modelMapper = modelMapper;
        this.mailSender = mailSender;
        this.restTemplate = restTemplate;
    }

    @Override
    public void buyTicket(BuyTicketRequest request) throws NotFoundException{
        VirtualVisit virtualVisit=virtualVisitService.findById(request.getVirtualVisitId());
        //setting real value just in case request is malicious
        request.setCashAmount(virtualVisit.getPrice());

        //contact bank
        TransactionRequest transactionRequest=modelMapper.map(request, TransactionRequest.class);
        boolean bankResponse=contactBank(transactionRequest);

        if(!bankResponse){
            throw new NotFoundException();
        }

        //insert ticket into database
        TicketEntity ticketEntity=modelMapper.map(request, TicketEntity.class);
        ticketEntity.setTicketId(null);
        ticketEntity.setTicketNumber(generateTicketNumber());
        repository.save(ticketEntity);

        //send mail
        try{
            sendMail(ticketEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
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

    private void sendMail(TicketEntity ticketEntity) throws NotFoundException, MessagingException {
        User user=userService.findById(ticketEntity.getUserId());
        VirtualVisit virtualVisit=virtualVisitService.findById(ticketEntity.getVirtualVisitId());
        Ticket ticket=modelMapper.map(ticketEntity, Ticket.class);

        //constructing mail
        MimeMessage message =mailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);

        mailMessage.setFrom(username);
        mailMessage.setTo(user.getMail());
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        ByteArrayOutputStream outputStream = null;

        //writing pdf to the output stream
        outputStream = new ByteArrayOutputStream();
        writePdf(outputStream, virtualVisit, ticket);
        byte[] bytes = outputStream.toByteArray();

        //construct the pdf body part
        DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");

        //add attachment
        mailMessage.addAttachment("ticket.pdf", dataSource);

        mailSender.send(message);
    }

    private void writePdf(ByteArrayOutputStream outputStream, VirtualVisit virtualVisit, Ticket ticket ) {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph p = new Paragraph("Virtual Visits Ticket");
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph();
        p.add(new Chunk("Ticket number: "+ ticket.getTicketNumber() +"\n", new Font(Font.TIMES_ROMAN, 12)));
        document.add(p);
        p=new Paragraph();
        p.add(new Chunk("Virtual visit of "+ virtualVisit.getMuseumName() +" starts on: "+ virtualVisit.getDate() +" at "+virtualVisit.getStart() +
                "h and will last "+ virtualVisit.getDuration(), new Font(Font.TIMES_ROMAN, 12)));
        document.add(p);

        document.close();
    }
}
