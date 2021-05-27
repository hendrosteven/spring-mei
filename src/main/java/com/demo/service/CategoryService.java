package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.model.Category;
import com.demo.repository.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Category findById(Long id){
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        return null; 
    }

    public List<Category> findByName(String name){
        return categoryRepo.findByCategoryName(name);
    }
    
}
