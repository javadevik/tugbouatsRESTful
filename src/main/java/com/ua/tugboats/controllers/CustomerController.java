package com.ua.tugboats.controllers;

import com.ua.tugboats.entities.CustomerEntity;
import com.ua.tugboats.exceptions.CustomerNotFoundException;
import com.ua.tugboats.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> findById(@RequestParam Long customerId) {
        try {
            CustomerEntity customerFound = customerService.findById(customerId);
            return new ResponseEntity<>(customerFound, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> findAll() {
        List<CustomerEntity> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> save(@RequestBody CustomerEntity customer) {
        CustomerEntity customerSaved = customerService.save(customer);
        return customerSaved != null
                ? new ResponseEntity<>(customerSaved, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam Long customerId,
                                    @RequestBody CustomerEntity customer) {
        try {
            CustomerEntity customerUpdated = customerService.update(customerId, customer);
            return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long customerId) {
        try {
            customerService.delete(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
