package com.ua.tugboats.services.impl;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.entities.SailorEntity;
import com.ua.tugboats.exceptions.SailorIsBusyException;
import com.ua.tugboats.exceptions.SailorNotFoundException;
import com.ua.tugboats.repositories.SailorRepository;
import com.ua.tugboats.services.SailorService;
import com.ua.tugboats.services.SailorStrippedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SailorServiceImpl implements SailorService, SailorStrippedService {

    private final SailorRepository sailorRepository;

    @Autowired
    public SailorServiceImpl(SailorRepository sailorRepository) {
        this.sailorRepository = sailorRepository;
    }

    @Override
    public SailorEntity findById(Long id) throws SailorNotFoundException {
        return sailorRepository.findById(id)
                .orElseThrow(
                        () -> new SailorNotFoundException("Cannot find sailor")
                );
    }

    @Override
    public List<SailorEntity> findAll() {
        return sailorRepository.findAll();
    }

    @Override
    public List<SailorEntity> findAllFree() {
        return sailorRepository
                .findAll()
                .stream()
                .filter(s -> s.getBoat() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<SailorEntity> findAllBusy() {
        return sailorRepository
                .findAll()
                .stream()
                .filter(s -> s.getBoat() != null)
                .collect(Collectors.toList());
    }

    @Override
    public BoatEntity findBoatBySailorId(Long sailorId) throws SailorNotFoundException {
        return findById(sailorId).getBoat();
    }

    @Override
    public SailorEntity save(SailorEntity sailor) {
        return sailorRepository.save(sailor);
    }

    @Override
    public SailorEntity update(Long sailorId, SailorEntity sailor) throws SailorNotFoundException {
        SailorEntity sailorToUpdate = findById(sailorId);

        sailorToUpdate.setFirstName(sailor.getFirstName());
        sailorToUpdate.setLastName(sailor.getLastName());
        sailorToUpdate.setSailorType(sailor.getSailorType());

        return sailorRepository.save(sailorToUpdate);
    }

    @Override
    public void delete(Long sailorId) throws SailorNotFoundException, SailorIsBusyException {
        SailorEntity sailorToDelete = findById(sailorId);

        if (sailorToDelete.getBoat() != null)
            throw new SailorIsBusyException("Sailor now is a member of crew");

        sailorRepository.delete(sailorToDelete);
    }
}
