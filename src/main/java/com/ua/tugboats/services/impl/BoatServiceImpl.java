package com.ua.tugboats.services.impl;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.entities.SailorEntity;
import com.ua.tugboats.exceptions.BoatIsBusyException;
import com.ua.tugboats.exceptions.BoatNotFoundException;
import com.ua.tugboats.exceptions.SailorNotFoundException;
import com.ua.tugboats.repositories.BoatRepository;
import com.ua.tugboats.services.BoatService;
import com.ua.tugboats.services.BoatStrippedService;
import com.ua.tugboats.services.SailorStrippedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoatServiceImpl implements BoatService, BoatStrippedService {

    private final BoatRepository boatRepository;
    private final SailorStrippedService sailorService;

    @Autowired
    public BoatServiceImpl(BoatRepository boatRepository, SailorStrippedService sailorService) {
        this.boatRepository = boatRepository;
        this.sailorService = sailorService;
    }

    @Override
    public BoatEntity findById(Long id) throws BoatNotFoundException {
        return boatRepository.findById(id)
                .orElseThrow(
                        () -> new BoatNotFoundException("Cannot find boat")
                );
    }

    @Override
    public List<BoatEntity> findAll() {
        return boatRepository.findAll();
    }

    @Override
    public List<BoatEntity> findAllFree() {
        return boatRepository
                .findAll()
                .stream()
                .filter(b -> !b.isBusy())
                .collect(Collectors.toList());
    }

    @Override
    public List<BoatEntity> findAllBusy() {
        return boatRepository
                .findAll()
                .stream()
                .filter(BoatEntity::isBusy)
                .collect(Collectors.toList());
    }

    @Override
    public BoatEntity save(Long[] sailorId, BoatEntity boat) throws SailorNotFoundException {
        List<SailorEntity> sailors = new ArrayList<>();

        for (Long id : sailorId)
            sailors.add(sailorService.findById(id));

        sailors.forEach(boat::addToSailors);

        return boatRepository.save(boat);
    }

    @Override
    public BoatEntity update(Long boatId, Long[] sailorId,
                             BoatEntity boat) throws SailorNotFoundException, BoatNotFoundException {

        BoatEntity boatToUpdate = findById(boatId);

        List<SailorEntity> sailors = new ArrayList<>();

        for (Long id : sailorId)
            sailors.add(sailorService.findById(id));

        boatToUpdate.clearSailors();

        sailors.forEach(boatToUpdate::addToSailors);

        boatToUpdate.setName(boat.getName());
        boatToUpdate.setBoatType(boat.getBoatType());

        return boatRepository.save(boatToUpdate);
    }

    @Override
    public void delete(Long boatId) throws BoatNotFoundException, BoatIsBusyException {
        BoatEntity boatToDelete = findById(boatId);

        if (boatToDelete.isBusy())
            throw new BoatIsBusyException("Boat in a journey");

        boatToDelete.clearSailors();
        boatRepository.delete(boatToDelete);
    }

}
