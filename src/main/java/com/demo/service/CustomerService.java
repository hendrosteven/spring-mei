package com.demo.service;

import com.demo.model.Customer;
import com.demo.repository.CustomerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepo customerRepo;

    public Customer create(String name, String email){
        Customer customer = Customer.builder().name(name).email(email).build();
        return customerRepo.save(customer);
    }

    public Iterable<Customer> findAll(){
        return customerRepo.findAll();
    }

    public void remove(Long id){
        customerRepo.deleteById(id);
    }
}
