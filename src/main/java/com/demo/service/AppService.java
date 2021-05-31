package com.demo.service;

import com.demo.model.App;
import com.demo.repository.AppRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppService implements UserDetailsService{

    @Autowired
    private AppRepo appRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appRepo.findByUserName(username);
    }

    public App register(App app){
        return appRepo.save(app);
    }

    public Iterable<App> findAll(){
        return appRepo.findAll();
    }
    
}
