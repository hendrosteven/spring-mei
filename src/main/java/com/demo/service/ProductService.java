package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.model.Product;
import com.demo.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Iterable<Product> findAll(int size, int page){
        Pageable pageable = PageRequest.of(page-1, size);
        Iterable<Product> data =  productRepo.findAll(pageable);
        System.out.println(size +" "+ page);
        return data;
    }

    public Iterable<Product> findAll(int size, int page, String field){
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(field).ascending());
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
}
