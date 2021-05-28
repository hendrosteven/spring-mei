package com.demo.contollers;

import com.demo.dto.CustomerRequest;
import com.demo.model.Customer;
import com.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Iterable<Customer> findAll(@RequestParam(value="isDeleted", 
        required = false, 
        defaultValue = "false") boolean isDeleted){

        return customerService.findAll(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){
        customerService.remove(id);
    }
}
