package com.ua.tugboats.repositories;

import com.ua.tugboats.entities.JourneyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JourneyRepository extends CrudRepository<JourneyEntity, Long> {
    List<JourneyEntity> findAll();
}
