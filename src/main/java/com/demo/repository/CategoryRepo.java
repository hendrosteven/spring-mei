package com.demo.repository;

import java.util.List;

import com.demo.model.Category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    
    @Query("SELECT c FROM Category c WHERE c.name = :param")
    public List<Category> findByCategoryName(@Param("param")String name);
}
