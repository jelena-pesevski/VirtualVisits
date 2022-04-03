package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.IntegrityException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Museum;
import org.unibl.etf.virtualvisits.models.MuseumDetails;
import org.unibl.etf.virtualvisits.models.entities.MuseumEntity;
import org.unibl.etf.virtualvisits.models.requests.MuseumRequest;
import org.unibl.etf.virtualvisits.repositories.MuseumEntityRepository;
import org.unibl.etf.virtualvisits.services.MuseumService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MuseumServiceImpl implements MuseumService {

    private final ModelMapper modelMapper;

    private final MuseumEntityRepository museumEntityRepository;

    public MuseumServiceImpl(MuseumEntityRepository repository, ModelMapper modelMapper) {
        this.museumEntityRepository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Museum> findAll() {
        return museumEntityRepository.findAll().stream().map(m->modelMapper.map(m, Museum.class)).collect(Collectors.toList());
    }

    @Override
    public MuseumDetails findById(Integer id) throws NotFoundException {
        return modelMapper.map(museumEntityRepository.findById(id).orElseThrow(NotFoundException::new), MuseumDetails.class);
    }

    @Override
    public List<Museum> findByName(String name) throws NotFoundException {
        return museumEntityRepository.getAllByNameStartingWith(name).stream().map(m->modelMapper.map(m, Museum.class)).collect(Collectors.toList());
    }

    @Override
    public List<Museum> findByCity(String city) throws NotFoundException {
        return museumEntityRepository.getAllByCityStartingWith(city).stream().map(m->modelMapper.map(m,Museum.class)).collect(Collectors.toList());
    }

    @Override
    public List<MuseumDetails> findAllDetailed() {
        return museumEntityRepository.findAll().stream().map(m->modelMapper.map(m, MuseumDetails.class)).collect(Collectors.toList());
    }

    @Override
    public MuseumDetails insert(MuseumRequest museumRequest) {
        MuseumEntity museumEntity=modelMapper.map(museumRequest, MuseumEntity.class);
        museumEntity.setMuseumId(null);
        museumEntity=museumEntityRepository.save(museumEntity);
        return modelMapper.map(museumEntity, MuseumDetails.class);
    }

    @Override
    public MuseumDetails update(Integer id, MuseumRequest museumRequest) throws NotFoundException {
        if (!museumEntityRepository.existsById(id))
            throw new NotFoundException();
        MuseumEntity museumEntity=modelMapper.map(museumRequest, MuseumEntity.class);
        museumEntity.setMuseumId(id);
        museumEntity=museumEntityRepository.save(museumEntity);
        return modelMapper.map(museumEntity, MuseumDetails.class);
    }

    @Override
    public void delete(Integer id) throws NotFoundException, IntegrityException {
        if (!museumEntityRepository.existsById(id))
            throw new NotFoundException();
        try{
            museumEntityRepository.deleteById(id);
        }catch(Exception e){
            throw new IntegrityException();
        }
    }
}
