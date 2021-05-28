package com.demo.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import com.demo.dto.OrderConfirm;
import com.demo.dto.OrderRequest;
import com.demo.model.Customer;
import com.demo.model.Order;
import com.demo.model.Product;
import com.demo.repository.CustomerRepo;
import com.demo.repository.OrderRepo;
import com.demo.repository.ProductRepo;
import com.demo.utility.PaymentUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public OrderConfirm createOrder(OrderRequest orderRequest){

        Optional<Customer> customer = customerRepo.findById(orderRequest.getCustomerId());
        if(!customer.isPresent()){
            throw new RuntimeException("Customer not found");
        }
        Optional<Product> product = productRepo.findById(orderRequest.getProductId()); 
        if(!product.isPresent()){
            throw new RuntimeException("Product not found");
        }

        Order order = Order.builder().customer(customer.get()).product(product.get()).orderDate(new Date()).build();
        orderRepo.save(order);

        PaymentUtility.validatePayment(customer.get().getEmail(), product.get().getPrice());

        return OrderConfirm.builder().status(true).order(order).build();

    }
}
