package com.ua.tugboats.services;

import com.ua.tugboats.entities.JourneyEntity;
import com.ua.tugboats.exceptions.JourneyNotFoundException;

import java.util.List;

public interface JourneyService {
    JourneyEntity findById(Long id) throws JourneyNotFoundException;

    List<JourneyEntity> findAll();

    List<JourneyEntity> findAllInTransit();

    JourneyEntity save(Long customerId, Long boatId, String startDate,
                       String endDate, JourneyEntity journey) throws Exception;

    JourneyEntity update(Long journeyId, Long customerId, Long boatId, String startDate,
                         String endDate, JourneyEntity journey) throws Exception;

    void delete(Long journeyId) throws JourneyNotFoundException;
}
