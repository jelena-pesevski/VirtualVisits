package org.unibl.etf.virtualvisits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Museum;
import org.unibl.etf.virtualvisits.models.SingleMuseum;
import org.unibl.etf.virtualvisits.models.entities.MuseumEntity;
import org.unibl.etf.virtualvisits.services.MuseumService;

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

    @GetMapping("/{id}")
    public SingleMuseum findById(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }

    @GetMapping("/searchName")
    public List<Museum> findByName(@RequestParam String name) throws NotFoundException{
        return service.findByName(name);
    }

    @GetMapping("/searchCity")
    public List<Museum> findByCity(@RequestParam String city) throws NotFoundException{
        return service.findByCity(city);
    }

}
