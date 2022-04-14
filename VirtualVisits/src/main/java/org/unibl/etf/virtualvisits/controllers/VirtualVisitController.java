package org.unibl.etf.virtualvisits.controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.virtualvisits.exceptions.BadRequest;
import org.unibl.etf.virtualvisits.exceptions.IntegrityException;
import org.unibl.etf.virtualvisits.exceptions.InvalidTicketException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.VirtualVisit;
import org.unibl.etf.virtualvisits.models.requests.AttendVisitRequest;
import org.unibl.etf.virtualvisits.models.responses.AttendVisitResponse;
import org.unibl.etf.virtualvisits.services.VirtualVisitService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/virtual-visits")
public class VirtualVisitController {

    private final VirtualVisitService service;

    public VirtualVisitController(VirtualVisitService service) {
        this.service = service;
    }

    @GetMapping
    public List<VirtualVisit> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public VirtualVisit findById(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }

    @GetMapping("/active")
    public List<VirtualVisit> findAllActive(){
        return service.findAllActive();
    }


    @GetMapping("/upcoming/museum")
    public List<VirtualVisit> findAllUpcomingForMuseum(@RequestParam Integer id) {
        return service.findAllUpcomingByMuseumId(id);
    }

    @GetMapping("/upcoming")
    public List<VirtualVisit> findAllUpcoming() {
        return service.findAllUpcoming();
    }

    @PostMapping("/attend")
    public AttendVisitResponse attendVirtualVisit(@RequestBody AttendVisitRequest attendVisitRequest) throws InvalidTicketException {
        return service.attendVirtualVisit(attendVisitRequest);
    }

    @GetMapping(value = "/tour-image/{virtualVisitId}/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable Integer virtualVisitId, @PathVariable String fileName) throws NotFoundException {
        byte[] bytes = service.getImage(virtualVisitId,  fileName);

        if(fileName.endsWith(".png")){
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(bytes);
        }else {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }
    }

    @GetMapping(value = "/tour-video/{virtualVisitId}/{fileName}", produces = "video/mp4")
    public ResponseEntity<Resource> getVideo(@PathVariable Integer virtualVisitId, @PathVariable String fileName) throws NotFoundException{
        return ResponseEntity
                .ok(service.getVideo(virtualVisitId, fileName));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws NotFoundException, IntegrityException {
        service.delete(id);
    }

    @PostMapping()
    public VirtualVisit insert(@RequestParam("virtualVisit") String virtualVisit, @RequestParam("image")MultipartFile[] images, @RequestParam(value = "video", required = false) MultipartFile video) throws BadRequest {
       VirtualVisit visit=null;

       //check if images size is ok
       if(images==null || images.length<5 || images.length>10){
           throw new BadRequest();
       }

       try{
          visit=service.insert(virtualVisit, images, video);
       }catch(Exception e){
           e.printStackTrace();
           //bad virtual visit string
           throw new BadRequest();

       }

       if(visit==null){
           throw new BadRequest();
       }

       return visit;
    }

    @PutMapping("/{id}")
    public VirtualVisit update(@PathVariable Integer id, @RequestParam("virtualVisit") String virtualVisit, @RequestParam(value = "image", required = false)MultipartFile[] images, @RequestParam(value = "video", required = false) MultipartFile video) throws BadRequest ,NotFoundException{
        VirtualVisit visit=null;

        //check if images size is ok
       if(images!=null && (images.length<5 || images.length>10)){
           throw new BadRequest();
       }

        visit=service.update(id, virtualVisit, images, video);
        return visit;
    }
}
