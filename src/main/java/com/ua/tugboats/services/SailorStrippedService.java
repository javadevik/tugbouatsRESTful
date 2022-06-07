package com.ua.tugboats.services;

import com.ua.tugboats.entities.SailorEntity;
import com.ua.tugboats.exceptions.SailorNotFoundException;

public interface SailorStrippedService {
    SailorEntity findById(Long id) throws SailorNotFoundException;
}
