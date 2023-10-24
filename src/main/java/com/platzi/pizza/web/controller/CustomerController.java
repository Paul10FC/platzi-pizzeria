package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.service.CustomerService;
import com.platzi.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phoneNumber){
        return ResponseEntity.ok(customerService.findByPhone(phoneNumber));
    }

    @RequestMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getByCustomerId(id));
    }
}
