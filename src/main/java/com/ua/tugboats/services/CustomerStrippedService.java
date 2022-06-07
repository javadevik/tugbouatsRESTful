package com.ua.tugboats.services;

import com.ua.tugboats.entities.CustomerEntity;
import com.ua.tugboats.exceptions.CustomerNotFoundException;

public interface CustomerStrippedService {
    CustomerEntity findById(Long id) throws CustomerNotFoundException;
}
