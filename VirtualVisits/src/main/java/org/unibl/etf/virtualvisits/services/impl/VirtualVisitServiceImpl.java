package org.unibl.etf.virtualvisits.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.virtualvisits.exceptions.BadRequest;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    private final ObjectMapper objectMapper;

    @Value("${visits.root.folder}")
    private String rootFolder;

    @Value("${tour-image.url}")
    private String tourImageUrl;

    @Value("${tour-video.url}")
    private String tourVideoUrl;

    @Value("${visits.root.folder}")
    private String visitsRootFolder;

    public VirtualVisitServiceImpl(VirtualVisitEntityRepository repository, ModelMapper modelMapper, TicketEntityRepository ticketEntityRepository, LogService logService, ObjectMapper objectMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.ticketEntityRepository = ticketEntityRepository;
        this.logService = logService;
        this.objectMapper = objectMapper;
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

            //delete folder
            File folder=new File(visitsRootFolder+id);
            for(File f:folder.listFiles()){
                f.delete();
            }
            folder.delete();

            JwtUser user=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logService.insert(new LogEntity(0, user.getUsername()+" deleted visit with id "+id, "VISIT-DELETE", Instant.now()));
        }catch(Exception e){
            throw new IntegrityException();
        }
    }

    @Override
    public VirtualVisit insert(String virtualVisit, MultipartFile[] images, MultipartFile video) throws JsonProcessingException {
        VirtualVisit visit=objectMapper.readValue(virtualVisit, VirtualVisit.class);
        //map to entity
        VirtualVisitEntity entity=modelMapper.map(visit, VirtualVisitEntity.class);
        entity.setVirtualVisitId(null);
        entity.setFolder("");
        entity=repository.save(entity);

        String folderName=entity.getVirtualVisitId().toString();
        entity.setFolder(folderName);
        repository.save(entity);

        File folderForVV=new File(visitsRootFolder+folderName);
        folderForVV.mkdir();

        //save images
        for(MultipartFile image : images){
            Path destinationFile = folderForVV.toPath().resolve(
                            Paths.get(image.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            try{
                InputStream inputStream = image.getInputStream();
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //save video if no yt link is sent
        if(video!=null && !video.isEmpty() && visit.getYtLink()==null){
            Path destinationFile = folderForVV.toPath().resolve(
                            Paths.get(video.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            try{
                InputStream inputStream = video.getInputStream();
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        JwtUser user=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logService.insert(new LogEntity(0, user.getUsername()+" added visit with id "+entity.getVirtualVisitId(), "VISIT-ADD", Instant.now()));
        return modelMapper.map(entity, VirtualVisit.class);
    }

    @Override
    public VirtualVisit update(Integer id, String virtualVisit, MultipartFile[] images, MultipartFile video) throws BadRequest,NotFoundException {
        VirtualVisit visit=null;

        if(!repository.existsById(id)){
            throw new NotFoundException();
        }
        try{
            visit=objectMapper.readValue(virtualVisit, VirtualVisit.class);
        }catch(Exception e){
            throw new BadRequest();
        }

        //check if no ticket is bought
        int numOfTickets=ticketEntityRepository.countByVirtualVisitId(id);
        if(numOfTickets!=0){
            System.out.println("ima karata");
            throw new NotFoundException();
        }

        //map to entity
        VirtualVisitEntity entity=modelMapper.map(visit, VirtualVisitEntity.class);
        entity.setVirtualVisitId(id);
        entity.setFolder(id.toString());
        repository.save(entity);

        File folderForVV=new File(visitsRootFolder+id);

        //updateImages
        if(images!=null){
            //delete prev images
            for(File f:folderForVV.listFiles()){
                if(!f.getName().endsWith(".mp4")){
                    f.delete();
                }
            }


            for(MultipartFile image : images){
                Path destinationFile = folderForVV.toPath().resolve(
                                Paths.get(image.getOriginalFilename()))
                        .normalize().toAbsolutePath();

                try{
                    InputStream inputStream = image.getInputStream();
                    Files.copy(inputStream, destinationFile,
                            StandardCopyOption.REPLACE_EXISTING);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        //if yt link is sent, delete video from last update
        if(visit.getYtLink()!=null){
            for(File f:folderForVV.listFiles()){
                if(f.getName().endsWith(".mp4")){
                    f.delete();
                }
            }
        }

        //save video if no yt link is sent
        if(video!=null && !video.isEmpty() && visit.getYtLink()==null){
            //delete prev video if exists
            for(File f:folderForVV.listFiles()){
                if(f.getName().endsWith(".mp4")){
                    f.delete();
                }
            }

            Path destinationFile = folderForVV.toPath().resolve(
                            Paths.get(video.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            try{
                InputStream inputStream = video.getInputStream();
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        JwtUser user=(JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logService.insert(new LogEntity(0, user.getUsername()+" updated visit with id "+entity.getVirtualVisitId(), "VISIT-UPDATE", Instant.now()));
        return modelMapper.map(entity, VirtualVisit.class);
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
