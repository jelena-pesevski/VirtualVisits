package org.unibl.etf.virtualvisits.services;

import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Museum;
import org.unibl.etf.virtualvisits.models.SingleMuseum;
import org.unibl.etf.virtualvisits.models.entities.MuseumEntity;

import java.util.List;

public interface MuseumService {

     List<Museum> findAll();

     SingleMuseum findById(Integer id) throws NotFoundException;

     List<Museum> findByName(String name) throws NotFoundException;

     List<Museum> findByCity(String city) throws NotFoundException;

}
