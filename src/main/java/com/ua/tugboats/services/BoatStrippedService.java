package com.ua.tugboats.services;

import com.ua.tugboats.entities.BoatEntity;
import com.ua.tugboats.exceptions.BoatNotFoundException;

public interface BoatStrippedService {
    BoatEntity findById(Long id) throws BoatNotFoundException;
}
