package com.ua.tugboats.controllers;

import com.ua.tugboats.entities.JourneyEntity;
import com.ua.tugboats.exceptions.JourneyNotFoundException;
import com.ua.tugboats.services.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journeys")
@CrossOrigin(origins = "http://localhost:3000")
public class JourneyController {

    private final JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long journeyId) {
        try {
            JourneyEntity journeyFound = journeyService.findById(journeyId);
            return new ResponseEntity<>(journeyFound, HttpStatus.OK);
        } catch (JourneyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<JourneyEntity>> findAll() {
        List<JourneyEntity> journeys = journeyService.findAll();
        return new ResponseEntity<>(journeys, HttpStatus.OK);
    }

    @GetMapping("/intransit")
    public ResponseEntity<List<JourneyEntity>> findAllInTransit() {
        List<JourneyEntity> journeys = journeyService.findAllInTransit();
        return new ResponseEntity<>(journeys, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam Long customerId,
                                  @RequestParam Long boatId,
                                  @RequestParam String startDate,
                                  @RequestParam String endDate,
                                  @RequestBody JourneyEntity journey) {
        try {
            JourneyEntity journeySaved = journeyService.save(customerId, boatId, startDate, endDate, journey);
            return new ResponseEntity<>(journeySaved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long journeyId,
                                    @RequestParam Long customerId,
                                    @RequestParam Long boatId,
                                    @RequestParam String startDate,
                                    @RequestParam String endDate,
                                    @RequestBody JourneyEntity journey) {
        try {
            JourneyEntity journeyUpdated = journeyService.update(journeyId, customerId, boatId, startDate, endDate, journey);
            return new ResponseEntity<>(journeyUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long journeyId) {
        try {
            journeyService.delete(journeyId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JourneyNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
