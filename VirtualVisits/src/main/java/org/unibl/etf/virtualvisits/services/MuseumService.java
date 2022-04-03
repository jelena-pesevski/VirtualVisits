package org.unibl.etf.virtualvisits.services;

import org.unibl.etf.virtualvisits.exceptions.IntegrityException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Museum;
import org.unibl.etf.virtualvisits.models.MuseumDetails;
import org.unibl.etf.virtualvisits.models.requests.MuseumRequest;

import java.util.List;

public interface MuseumService {

     List<Museum> findAll();

     MuseumDetails findById(Integer id) throws NotFoundException;

     List<Museum> findByName(String name) throws NotFoundException;

     List<Museum> findByCity(String city) throws NotFoundException;

     List<MuseumDetails> findAllDetailed();

     MuseumDetails insert(MuseumRequest museumRequest);

     MuseumDetails update(Integer id, MuseumRequest museumRequest) throws NotFoundException;

     void delete(Integer id) throws NotFoundException, IntegrityException;
}
