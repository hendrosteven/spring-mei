package com.demo.contollers;

import java.util.List;

import javax.validation.Valid;

import com.demo.dto.DataResponse;
import com.demo.dto.ProductRequest;
import com.demo.dto.SearchRequest;
import com.demo.model.Product;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import com.demo.utility.ErrorParsingUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<DataResponse> createOne(@Valid @RequestBody ProductRequest productRequest, Errors errors) {

        DataResponse response = new DataResponse();
        if(errors.hasErrors()){
            response.setStatus(false);
            response.setMessages(ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }else{
            try{
                Product product = new Product();
                product.setCode(productRequest.getCode());
                product.setName(productRequest.getName());
                product.setDescription(productRequest.getDescription());
                product.setPrice(productRequest.getPrice());
                product.setCategory(categoryService.findById(productRequest.getCategoryId()));

                service.save(product);
                
                response.setStatus(true);
                response.getMessages().add("Product saved");
                response.setPayload(product);

                return ResponseEntity.ok(response);
            }catch(Exception ex){
                response.setStatus(false);
                response.getMessages().add(ex.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
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
