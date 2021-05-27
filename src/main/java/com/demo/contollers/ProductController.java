package com.demo.contollers;

import java.util.List;

import com.demo.dto.SearchRequest;
import com.demo.model.Product;
import com.demo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product createOne(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping("/{size}/{page}")
    public Iterable<Product> findAll(@PathVariable("size") int size, @PathVariable("page") int page) {
        return service.findAll(size, page);
    }

    @GetMapping("/{size}/{page}/{field}")
    public Iterable<Product> findAll(@PathVariable("size") int size, 
        @PathVariable("page") int page, 
        @PathVariable("field") String field) {

        return service.findAll(size, page, field);
    }


    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return service.findOne(id);
    }

    @PostMapping("/search")
    public List<Product> findByName(@RequestBody SearchRequest search) {
        return service.findByName(search.getKey());
    }

    @GetMapping("/category/{id}")
    public List<Product> findByCategoryId(@PathVariable("id") Long id) {
        return service.findByCategoryId(id);
    }
}
