package com.ua.tugboats.services;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.entities.SailorEntity;
import com.ua.tugboats.exceptions.SailorIsBusyException;
import com.ua.tugboats.exceptions.SailorNotFoundException;

import java.util.List;

public interface SailorService {

    SailorEntity findById(Long id) throws SailorNotFoundException;

    List<SailorEntity> findAll();

    List<SailorEntity> findAllFree();

    List<SailorEntity> findAllBusy();

    BoatEntity findBoatBySailorId(Long sailor_id) throws SailorNotFoundException;

    SailorEntity save(SailorEntity sailor);

    SailorEntity update(Long sailor_id, SailorEntity sailor) throws SailorNotFoundException;

    void delete(Long sailor_id) throws SailorNotFoundException, SailorIsBusyException;
}
