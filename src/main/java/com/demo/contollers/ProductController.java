package com.demo.contollers;

import java.util.List;

import com.demo.model.Product;
import com.demo.service.FakeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private FakeService service;

    @PostMapping
    public Product createOne(@RequestBody Product product) {
        return service.createOne(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.findProducts();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") int id) {
        return service.findOneById(id);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") int id) {
        service.removeById(id);
    }
}
