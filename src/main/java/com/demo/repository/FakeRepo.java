package com.demo.repository;

import java.util.List;

import com.demo.model.Product;

public interface FakeRepo {
    Product saveOne(Product product);
    List<Product> findAll();
    Product findOne(int id);
    void removeOne(int id);
}
