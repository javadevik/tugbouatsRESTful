package com.ua.tugboats.services;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.exceptions.BoatIsBusyException;
import com.ua.tugboats.exceptions.BoatNotFoundException;
import com.ua.tugboats.exceptions.SailorNotFoundException;

import java.util.List;

public interface BoatService {
    BoatEntity findById(Long id) throws BoatNotFoundException;

    List<BoatEntity> findAll();

    List<BoatEntity> findAllFree();

    List<BoatEntity> findAllBusy();

    BoatEntity save(Long[] sailorsId, BoatEntity boat) throws SailorNotFoundException;

    BoatEntity update(Long boatId, Long[] sailorsId,
                      BoatEntity boat) throws SailorNotFoundException, BoatNotFoundException;

    void delete(Long boat_id) throws BoatNotFoundException, BoatIsBusyException;
}
