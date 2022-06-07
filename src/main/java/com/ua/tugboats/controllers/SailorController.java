package com.ua.tugboats.controllers;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.entities.SailorEntity;
import com.ua.tugboats.exceptions.SailorIsBusyException;
import com.ua.tugboats.exceptions.SailorNotFoundException;
import com.ua.tugboats.services.SailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sailors")
@CrossOrigin(origins = "http://localhost:3000")
public class SailorController {

    private final SailorService sailorService;

    @Autowired
    public SailorController(SailorService sailorService) {
        this.sailorService = sailorService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam("sailorId") Long sailorId) {
        try {
            SailorEntity sailorFound = sailorService.findById(sailorId);
            return new ResponseEntity<>(sailorFound, HttpStatus.OK);
        } catch (SailorNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SailorEntity>> findAll() {
        List<SailorEntity> sailors = sailorService.findAll();
        return new ResponseEntity<>(sailors, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<SailorEntity>> findAllFree() {
        List<SailorEntity> sailorsFree = sailorService.findAllFree();
        return new ResponseEntity<>(sailorsFree, HttpStatus.OK);
    }

    @GetMapping("/busy")
    public ResponseEntity<List<SailorEntity>> findAllBusy() {
        List<SailorEntity> sailorsBusy = sailorService.findAllBusy();
        return new ResponseEntity<>(sailorsBusy, HttpStatus.OK);
    }

    @GetMapping("/boat")
    public ResponseEntity<?> findBoatBySailorId(@RequestParam Long sailorId) {
        try {
            BoatEntity boat = sailorService.findBoatBySailorId(sailorId);
            return new ResponseEntity<>(boat, HttpStatus.OK);
        } catch (SailorNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SailorEntity> create(@RequestBody SailorEntity sailor) {
        SailorEntity sailorSaved = sailorService.save(sailor);
        return sailorSaved != null
                ? new ResponseEntity<>(sailorSaved, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long sailorId,
                                    @RequestBody SailorEntity sailor) {
        try {
            SailorEntity sailorUpdate = sailorService.update(sailorId, sailor);
            return new ResponseEntity<>(sailorUpdate, HttpStatus.OK);
        } catch (SailorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long sailorId) {
        try {
            sailorService.delete(sailorId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SailorNotFoundException | SailorIsBusyException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
