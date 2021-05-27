package com.demo.contollers;

import com.demo.dto.SearchRequest;
import com.demo.model.Category;
import com.demo.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createOne(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") Long id){
        return categoryService.findById(id);
    }

    @PostMapping("/search")
    public Iterable<Category> findByName(@RequestBody SearchRequest search){
        return categoryService.findByName(search.getKey());
    }
    
}
