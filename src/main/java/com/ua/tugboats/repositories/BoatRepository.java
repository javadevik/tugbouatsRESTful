package com.ua.tugboats.repositories;

import com.ua.tugboats.entities.BoatEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoatRepository extends CrudRepository<BoatEntity, Long> {
    List<BoatEntity> findAll();
}
