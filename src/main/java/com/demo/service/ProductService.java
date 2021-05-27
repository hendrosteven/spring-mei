package com.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.demo.model.Product;
import com.demo.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Iterable<Product> findAll(int size, int page){
        Pageable pageable = PageRequest.of(page-1, size);
        return productRepo.findAll(pageable);
    }

    public Iterable<Product> findAll(int size, int page, String field){
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(field).descending());
        return productRepo.findAll(pageable);
    }

    public Product findOne(Long id){
        Optional<Product> product =  productRepo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    public List<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }

    public List<Product> findByCategoryId(Long categoryId){
        return productRepo.findByCategoryId(categoryId);
    }
}
