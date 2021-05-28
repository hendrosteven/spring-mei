package com.demo.repository;

import com.demo.model.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
    
}
