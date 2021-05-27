package com.demo.repository;

import java.util.List;

import com.demo.model.Product;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {
   
    public List<Product> findByName(String name);

    public List<Product> findByNameContains(String name);

    public List<Product> findByCategoryId(Long categoryId);

    public List<Product> findByNameContainsAndCategoryId(String name, Long categoryId);

}
