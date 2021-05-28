package com.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductRequest {
  
    @NotEmpty(message = "Code is required")
    @Size(min = 3, max = 5, message = "Code length must be 3 to 5 characters")
    @Pattern(regexp = "PD[0-9]+", message = "Code must be start with \'PD\'")
    private String code;

    @NotEmpty(message = "Name is required")
    private String name;
   
    private String description;

    private double price;

    private Long categoryId;
}
