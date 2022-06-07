package com.ua.tugboats.repositories;

import com.ua.tugboats.entities.SailorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SailorRepository extends CrudRepository<SailorEntity, Long> {
    List<SailorEntity> findAll();
}
