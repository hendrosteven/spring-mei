package com.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.demo.model.Product;

import org.springframework.stereotype.Component;

@Component
public class FakeRepoImpl implements FakeRepo {

    private static List<Product> products = new ArrayList<>();

    @Override
    public Product saveOne(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
       return products;
    }

    @Override
    public Product findOne(int id) {
        for(Product p: products) {
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public void removeOne(int id) {
       for(Product p: products) {
           if(p.getId() == id){
               products.remove(p);
           }
       }
    }
    
}
