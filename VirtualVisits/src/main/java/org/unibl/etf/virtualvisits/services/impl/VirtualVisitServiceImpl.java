package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.InvalidTicketException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.VirtualVisit;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;
import org.unibl.etf.virtualvisits.models.entities.VirtualVisitEntity;
import org.unibl.etf.virtualvisits.models.requests.AttendVisitRequest;
import org.unibl.etf.virtualvisits.models.responses.AttendVisitResponse;
import org.unibl.etf.virtualvisits.repositories.TicketEntityRepository;
import org.unibl.etf.virtualvisits.repositories.VirtualVisitEntityRepository;
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

    @Value("${visits.root.folder}")
    private String rootFolder;

    @Value("${tour-image.url}")
    private String tourImageUrl;

    @Value("${tour-video.url}")
    private String tourVideoUrl;

    public VirtualVisitServiceImpl(VirtualVisitEntityRepository repository, ModelMapper modelMapper, TicketEntityRepository ticketEntityRepository) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.ticketEntityRepository = ticketEntityRepository;
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
     /*   long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);
        VirtualVisitEntity virtualVisit=repository.findActiveVisitById(virtualVisitId, date, time).orElseThrow(NotFoundException::new);*/

        String relativePath=virtualVisitId+ File.separator+ fileName;
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
   /*     long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);
        VirtualVisitEntity virtualVisit=repository.findActiveVisitById(virtualVisitId, date, time).orElseThrow(NotFoundException::new);*/

        String relativePath=virtualVisitId+ File.separator+ fileName;
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
        //check if ticket is valid
        TicketEntity ticketEntity=ticketEntityRepository.findByVirtualVisitIdAndAndTicketNumber(request.getVirtualVisitId(), request.getTicketNumber()).orElseThrow(InvalidTicketException::new);

        //check if visit is active
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        Time time=new Time(millis);
        VirtualVisitEntity virtualVisit=repository.findActiveVisitById(request.getVirtualVisitId(), date, time).orElseThrow(InvalidTicketException::new);

        //ticket is valid if no exception is thrown
        //list all files from folder
        String folderPath=rootFolder+virtualVisit.getFolder();
        File virtualVisitRoot=new File(folderPath);

        File[] files=virtualVisitRoot.listFiles();

        //prepare AttendVisitResponse
        AttendVisitResponse response=new AttendVisitResponse();
        List<String> imagesUrls=new ArrayList<>();
        String videoUrl=null;

        if(virtualVisit.getYtLink()!=null){
            response.setYtLink(virtualVisit.getYtLink());
        }

        String reqBegin=tourImageUrl+virtualVisit.getFolder()+"/";
        for(File f:files){
            if(!f.getName().endsWith(".mp4")){
                imagesUrls.add(reqBegin+f.getName());
            }else{
                videoUrl=tourVideoUrl+virtualVisit.getFolder()+"/"+f.getName();
            }
        }

        response.setImagesUrls(imagesUrls);
        response.setVideoUrl(videoUrl);
        //adding ending time
        response.setEndingTimeInMillis(getEndingTime(virtualVisit));

        return response;
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
