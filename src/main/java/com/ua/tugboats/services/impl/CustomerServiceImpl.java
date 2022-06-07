package com.ua.tugboats.services.impl;

import com.ua.tugboats.entities.CustomerEntity;
import com.ua.tugboats.exceptions.CustomerNotFoundException;
import com.ua.tugboats.repositories.CustomerRepository;
import com.ua.tugboats.services.CustomerService;
import com.ua.tugboats.services.CustomerStrippedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService, CustomerStrippedService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity findById(Long id) throws CustomerNotFoundException {
        return customerRepository
                .findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Cannot find customer")
                );
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity save(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerEntity update(Long customerId, CustomerEntity customer) throws CustomerNotFoundException {
        CustomerEntity customerToUpdate = findById(customerId);

        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setPhone(customer.getPhone());

        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void delete(Long customerId) throws CustomerNotFoundException {
        CustomerEntity customerToDelete = findById(customerId);
        customerRepository.delete(customerToDelete);
    }
}
