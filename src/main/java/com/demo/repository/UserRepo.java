package com.demo.repository;

import com.demo.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    
    public User findByEmailAndPassword(String email, String password);
}
