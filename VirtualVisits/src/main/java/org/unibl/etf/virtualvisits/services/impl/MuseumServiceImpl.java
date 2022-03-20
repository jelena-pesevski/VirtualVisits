package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Museum;
import org.unibl.etf.virtualvisits.models.SingleMuseum;
import org.unibl.etf.virtualvisits.repositories.MuseumEntityRepository;
import org.unibl.etf.virtualvisits.services.MuseumService;

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
    public SingleMuseum findById(Integer id) throws NotFoundException {
        return modelMapper.map(museumEntityRepository.findById(id).orElseThrow(NotFoundException::new), SingleMuseum.class);
    }

    @Override
    public List<Museum> findByName(String name) throws NotFoundException {
        return museumEntityRepository.getAllByNameStartingWith(name).stream().map(m->modelMapper.map(m, Museum.class)).collect(Collectors.toList());
    }

    @Override
    public List<Museum> findByCity(String city) throws NotFoundException {
        return museumEntityRepository.getAllByCityStartingWith(city).stream().map(m->modelMapper.map(m,Museum.class)).collect(Collectors.toList());
    }
}
