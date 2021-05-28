package com.demo.repository;

import com.demo.model.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
    
}
