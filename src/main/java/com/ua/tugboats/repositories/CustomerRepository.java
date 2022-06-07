package com.ua.tugboats.repositories;

import com.ua.tugboats.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAll();
}
