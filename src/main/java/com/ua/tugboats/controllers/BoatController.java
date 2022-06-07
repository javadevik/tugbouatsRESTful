package com.ua.tugboats.controllers;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.exceptions.BoatIsBusyException;
import com.ua.tugboats.exceptions.BoatNotFoundException;
import com.ua.tugboats.exceptions.SailorNotFoundException;
import com.ua.tugboats.services.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boats")
@CrossOrigin(origins = "http://localhost:3000")
public class BoatController {

    private final BoatService boatService;

    @Autowired
    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long boatId) {
        try {
            BoatEntity boatFound = boatService.findById(boatId);
            return new ResponseEntity<>(boatFound, HttpStatus.OK);
        } catch (BoatNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<BoatEntity>> findAll() {
        List<BoatEntity> boats = boatService.findAll();
        return new ResponseEntity<>(boats, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<BoatEntity>> findAllFree() {
        List<BoatEntity> boatsFree = boatService.findAllFree();
        return new ResponseEntity<>(boatsFree, HttpStatus.OK);
    }

    @GetMapping("/busy")
    public ResponseEntity<List<BoatEntity>> findAllBusy() {
        List<BoatEntity> boatsBusy = boatService.findAllBusy();
        return new ResponseEntity<>(boatsBusy, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam Long[] sailorId, @RequestBody BoatEntity boat) {
        try {
            BoatEntity boatSaved = boatService.save(sailorId, boat);
            return new ResponseEntity<>(boatSaved, HttpStatus.CREATED);
        } catch (SailorNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long boatId,
                                    @RequestParam Long[] sailorId,
                                    @RequestBody BoatEntity boat) {
        try {
            BoatEntity boatUpdated = boatService.update(boatId, sailorId, boat);
            return new ResponseEntity<>(boatUpdated, HttpStatus.OK);
        } catch (SailorNotFoundException | BoatNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long boatId) {
        try {
            boatService.delete(boatId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BoatNotFoundException | BoatIsBusyException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
