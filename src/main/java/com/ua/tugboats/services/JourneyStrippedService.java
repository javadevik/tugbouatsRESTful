package com.ua.tugboats.services;

import com.ua.tugboats.entities.JourneyEntity;

import java.util.List;

public interface JourneyStrippedService {
    List<JourneyEntity> findAll();
}
