package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "tbl_products")
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =5, nullable = false, unique = true)
    private String code;

    @Column(name = "product_name", length =100, nullable = false)
    private String name;

    @Column(length =500, nullable = true)
    private String description;

    private double price;

    @ManyToOne
    private Category category;

    
}
