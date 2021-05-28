package com.demo.dto;

import com.demo.model.Order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderConfirm {
    private boolean status;
    private Order order;
}
