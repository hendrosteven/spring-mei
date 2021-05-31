package com.demo.repository;

import com.demo.model.App;

import org.springframework.data.repository.CrudRepository;

public interface AppRepo extends CrudRepository<App, Long>{

    public App findByUserName(String userName);
    
}
