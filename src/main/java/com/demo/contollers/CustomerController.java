package com.demo.contollers;

import com.demo.dto.CustomerRequest;
import com.demo.model.Customer;
import com.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createOne(@RequestBody CustomerRequest customer){
        return customerService.create(customer.getName(), customer.getEmail());
    }

    @GetMapping
    public Iterable<Customer> findAll(){
        return customerService.findAll();
    }
}
