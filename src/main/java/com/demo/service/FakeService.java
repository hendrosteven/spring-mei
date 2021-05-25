package com.demo.service;

import java.util.List;

import com.demo.model.Product;
import com.demo.repository.FakeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FakeService {
    
    @Autowired
    private FakeRepo fakeRepo;

    public Product createOne(Product product){
        return fakeRepo.saveOne(product);
    }

    public List<Product> findProducts(){
        return fakeRepo.findAll();
    }

    public Product findOneById(int id){
        return fakeRepo.findOne(id);
    }

    public void removeById(int id){
        fakeRepo.removeOne(id);
    }
}
