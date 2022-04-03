package org.unibl.etf.virtualvisits.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.virtualvisits.models.Log;
import org.unibl.etf.virtualvisits.services.LogService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<Log> findAll(){
        return logService.findAll();
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getPdf(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        byte[] contents =logService.getPdf();

        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        String filename = "logs_"+ sdf.format(new Date())+".pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }
}
