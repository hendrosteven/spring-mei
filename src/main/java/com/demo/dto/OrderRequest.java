package com.demo.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long customerId;
    private Long productId;
}
