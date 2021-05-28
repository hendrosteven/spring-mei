package com.demo.service;

import javax.persistence.EntityManager;

import com.demo.model.Customer;
import com.demo.repository.CustomerRepo;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private EntityManager entityManager;

    public Customer create(String name, String email){
        Customer customer = Customer.builder().name(name).email(email).build();
        return customerRepo.save(customer);
    }

    public Iterable<Customer> findAll(boolean isHapus){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCustomerFilter");
        filter.setParameter("isDeleted", isHapus);
        Iterable<Customer> customers = customerRepo.findAll();
        session.disableFilter("deletedCustomerFilter");
        return customers;
    }

    public void remove(Long id){
        customerRepo.deleteById(id);
    }
}
