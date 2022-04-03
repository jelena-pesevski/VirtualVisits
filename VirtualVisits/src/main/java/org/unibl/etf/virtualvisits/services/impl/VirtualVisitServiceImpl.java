package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.IntegrityException;
import org.unibl.etf.virtualvisits.exceptions.InvalidTicketException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.JwtUser;
import org.unibl.etf.virtualvisits.models.VirtualVisit;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;
import org.unibl.etf.virtualvisits.models.entities.VirtualVisitEntity;
import org.unibl.etf.virtualvisits.models.requests.AttendVisitRequest;
import org.unibl.etf.virtualvisits.models.responses.AttendVisitResponse;
import org.unibl.etf.virtualvisits.repositories.TicketEntityRepository;
import org.unibl.etf.virtualvisits.repositories.VirtualVisitEntityRepository;
import org.unibl.etf.virtualvisits.services.LogService;
import org.unibl.etf.virtualvisits.services.VirtualVisitService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirtualVisitServiceImpl implements VirtualVisitService {

    private final VirtualVisitEntityRepository repository;

    private final TicketEntityRepository ticketEntityRepository;

    private final ModelMapper modelMapper;

    private final LogService logService;

    @Value("${visits.root.folder}")
    private String rootFolder;

    @Value("${tour-image.url}")
    private String tourImageUrl;

    @Value("${tour-video.url}")
    private String tourVideoUrl;

    public VirtualVisitServiceImpl(VirtualVisitEntityRepository repository, ModelMapper modelMapper, TicketEntityRepository ticketEntityRepository, LogService logService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.ticketEntityRepository = ticketEntityRepository;
        this.logService = logService;
    }

    @Override
    public List<VirtualVisit> findAll() {
        return repository.findAll().stream().map(v->modelMapper.map(v, VirtualVisit.class)).collect(Collectors.toList());
    }

    @Override
    public VirtualVisit findById(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new), VirtualVisit.class);
    }

    @Override
    public List<VirtualVisit> findAllActive() {
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);

        return repository.findAllActiveVisits(date, time).stream().map(v->modelMapper.map(v, VirtualVisit.class)).collect(Collectors.toList());
    }

    @Override
    public List<VirtualVisit> findAllUpcomingByMuseumId(Integer id) {
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);

        return repository.findAllUpcomingByMuseumId(date, time, id).stream().map(v->modelMapper.map(v, VirtualVisit.class)).collect(Collectors.toList());
    }

    @Override
    public byte[] getImage(Integer virtualVisitId, String fileName) throws NotFoundException {
        //check if virtual visit is active, if it is not exception will be thrown
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);
        VirtualVisitEntity virtualVisit=repository.findActiveVisitById(virtualVisitId, date, time).orElseThrow(NotFoundException::new);

        String relativePath=virtualVisit.getFolder()+ File.separator+ fileName;
        File file=new File(rootFolder+relativePath);
        if(!file.exists()){
            throw new NotFoundException();
        }
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    @Override
    public ByteArrayResource getVideo(Integer virtualVisitId, String fileName) throws NotFoundException {
        //check if virtual visit is active, if it is not exception will be thrown
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);
        VirtualVisitEntity virtualVisit=repository.findActiveVisitById(virtualVisitId, date, time).orElseThrow(NotFoundException::new);

        String relativePath=virtualVisit.getFolder()+ File.separator+ fileName;
        File file=new File(rootFolder+relativePath);
        if(!file.exists()){
            throw new NotFoundException();
        }
        try {
            return new ByteArrayResource(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    @Override
    public AttendVisitResponse attendVirtualVisit(AttendVisitRequest request) throws InvalidTicketException {
        JwtUser user= (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            //check if ticket is valid
            TicketEntity ticketEntity = ticketEntityRepository.findByVirtualVisitIdAndAndTicketNumber(request.getVirtualVisitId(), request.getTicketNumber()).orElseThrow(InvalidTicketException::new);

            //if user id from ticket is not id for currently authenticated user
            if(!ticketEntity.getUserId().equals(user.getId())){
                throw new InvalidTicketException();
            }

            //check if visit is active
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            Time time = new Time(millis);
            VirtualVisitEntity virtualVisit = repository.findActiveVisitById(request.getVirtualVisitId(), date, time).orElseThrow(InvalidTicketException::new);

            //ticket is valid if no exception is thrown
            logService.insert(new LogEntity(0, "Sending url-s for visit "+ virtualVisit.getVirtualVisitId()+" to user:"+ user.getUsername(), "ATTEND-VISIT", Instant.now()));
            //list all files from folder
            String folderPath = rootFolder + virtualVisit.getFolder();
            File virtualVisitRoot = new File(folderPath);

            File[] files = virtualVisitRoot.listFiles();

            //prepare AttendVisitResponse
            AttendVisitResponse response = new AttendVisitResponse();
            List<String> imagesUrls = new ArrayList<>();
            String videoUrl = null;

            if (virtualVisit.getYtLink() != null) {
                response.setYtLink(virtualVisit.getYtLink());
            }

            String reqBegin = tourImageUrl + virtualVisit.getVirtualVisitId() + "/";
            for (File f : files) {
                if (!f.getName().endsWith(".mp4")) {
                    imagesUrls.add(reqBegin + f.getName());
                } else {
                    videoUrl = tourVideoUrl + virtualVisit.getVirtualVisitId() + "/" + f.getName();
                }
            }

            response.setImagesUrls(imagesUrls);
            response.setVideoUrl(videoUrl);
            //adding ending time
            response.setEndingTimeInMillis(getEndingTime(virtualVisit));
            return response;
        }catch(InvalidTicketException e){
            logService.insert(new LogEntity(0, "Attempt to attend virtual visit failed for user:"+ user.getUsername(), "ATTEND-FAIL", Instant.now()));
            throw e;
        }
    }

    @Override
    public List<VirtualVisit> findAllUpcoming() {
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);

        return repository.findAllUpcoming(date, time).stream().map(v->modelMapper.map(v, VirtualVisit.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) throws NotFoundException, IntegrityException {
        if (!repository.existsById(id))
            throw new NotFoundException();
        try{
            repository.deleteById(id);
        }catch(Exception e){
            throw new IntegrityException();
        }
    }

    private long getEndingTime(VirtualVisitEntity virtualVisit){
        LocalDate localDate = virtualVisit.getDate().toLocalDate();
        LocalTime localTime=virtualVisit.getStart().toLocalTime();
        LocalTime durationTime=virtualVisit.getDuration().toLocalTime();

        LocalDateTime ldt=localDate.atTime(localTime);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        Instant instant=zdt.toInstant().plus(durationTime.getHour(), ChronoUnit.HOURS).plus(durationTime.getMinute(), ChronoUnit.MINUTES);

        return instant.toEpochMilli();
    }
}
