package org.unibl.etf.virtualvisits.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualvisits.exceptions.IntegrityException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Museum;
import org.unibl.etf.virtualvisits.models.MuseumDetails;
import org.unibl.etf.virtualvisits.models.requests.MuseumRequest;
import org.unibl.etf.virtualvisits.services.MuseumService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/museums")
public class MuseumController {

    private final MuseumService service;

    public MuseumController(MuseumService service) {
        this.service = service;
    }

    @GetMapping
    public List<Museum> findAll(){
        return service.findAll();
    }

    @GetMapping("/detailed")
    public List<MuseumDetails> findAllDetailed(){ return service.findAllDetailed();}

    @GetMapping("/{id}")
    public MuseumDetails findById(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MuseumDetails insert(@RequestBody @Valid MuseumRequest museum){
        return service.insert(museum);
    }

    @PutMapping("/{id}")
    public MuseumDetails update(@PathVariable Integer id, @RequestBody @Valid MuseumRequest museum) throws  NotFoundException{
        return service.update(id, museum);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws NotFoundException, IntegrityException {
        service.delete(id);
    }
}
