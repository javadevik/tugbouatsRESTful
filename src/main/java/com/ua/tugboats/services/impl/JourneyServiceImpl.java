package com.ua.tugboats.services.impl;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.entities.CustomerEntity;
import com.ua.tugboats.entities.JourneyEntity;
import com.ua.tugboats.exceptions.JourneyNotFoundException;
import com.ua.tugboats.repositories.JourneyRepository;
import com.ua.tugboats.services.BoatStrippedService;
import com.ua.tugboats.services.CustomerStrippedService;
import com.ua.tugboats.services.JourneyService;
import com.ua.tugboats.services.JourneyStrippedService;
import com.ua.tugboats.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JourneyServiceImpl implements JourneyService, JourneyStrippedService {

    private final JourneyRepository journeyRepository;
    private final CustomerStrippedService customerService;
    private final BoatStrippedService boatService;
    private final DateService dateService;

    @Autowired
    public JourneyServiceImpl(JourneyRepository journeyRepository, CustomerStrippedService customerService,
                              BoatStrippedService boatService, DateService dateService) {
        this.journeyRepository = journeyRepository;
        this.customerService = customerService;
        this.boatService = boatService;
        this.dateService = dateService;
    }

    @Override
    public JourneyEntity findById(Long id) throws JourneyNotFoundException {
        return journeyRepository.findById(id)
                .orElseThrow(
                        () -> new JourneyNotFoundException("Cannot find journey")
                );
    }

    @Override
    public List<JourneyEntity> findAll() {
        return journeyRepository.findAll();
    }

    @Override
    public List<JourneyEntity> findAllInTransit() {
        Timestamp currentTimestamp = dateService.getCurrentDate();
        return journeyRepository
                .findAll()
                .stream()
                .filter(j -> j.getEndDate().compareTo(currentTimestamp) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public JourneyEntity save(Long customerId, Long boatId, String startDate,
                              String endDate, JourneyEntity journey) throws Exception {
        CustomerEntity customer = customerService.findById(customerId);
        BoatEntity boat = boatService.findById(boatId);

        Timestamp startTimestamp = dateService.parseToTimestamp(startDate);
        Timestamp endTimestamp = dateService.parseToTimestamp(endDate);

        journey.setStartDate(startTimestamp);
        journey.setEndDate(endTimestamp);

        journey.setCustomer(customer);

        boat.setBusy(true);
        journey.setBoat(boat);

        return journeyRepository.save(journey);
    }

    @Override
    public JourneyEntity update(Long journeyId, Long customerId, Long boatId, String startDate,
                                String endDate, JourneyEntity journey) throws Exception {
        JourneyEntity journeyToUpdate = findById(journeyId);

        CustomerEntity customer = customerService.findById(customerId);
        BoatEntity boat = boatService.findById(boatId);

        Timestamp startTimestamp = dateService.parseToTimestamp(startDate);
        Timestamp endTimestamp = dateService.parseToTimestamp(endDate);

        if (!journeyToUpdate.getBoat().equals(boat)) {
            journeyToUpdate.getBoat().setBusy(false);
            journeyToUpdate.setBoat(boat);
            boat.setBusy(true);
        }

        journeyToUpdate.setStartDate(startTimestamp);
        journeyToUpdate.setEndDate(endTimestamp);

        journeyToUpdate.setBeginning(journey.getBeginning());
        journeyToUpdate.setDestination(journey.getDestination());

        journeyToUpdate.setCargoType(journey.getCargoType());

        journeyToUpdate.setWeight(journey.getWeight());

        journeyToUpdate.setCustomer(customer);

        return journeyRepository.save(journeyToUpdate);
    }

    @Override
    public void delete(Long journeyId) throws JourneyNotFoundException {
        JourneyEntity journeyToDelete = findById(journeyId);
        journeyToDelete.getBoat().setBusy(false);
        journeyRepository.delete(journeyToDelete);
    }

}
