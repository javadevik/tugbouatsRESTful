package com.ua.tugboats.services;

import com.ua.tugboats.entities.CustomerEntity;
import com.ua.tugboats.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerEntity findById(Long id) throws CustomerNotFoundException;

    List<CustomerEntity> findAll();

    CustomerEntity save(CustomerEntity customer);

    CustomerEntity update(Long customerId, CustomerEntity customer) throws CustomerNotFoundException;

    void delete(Long customerId) throws CustomerNotFoundException;
}
