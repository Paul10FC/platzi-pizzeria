package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.service.OrderService;
import com.platzi.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/all")
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @RequestMapping("/today")
    public ResponseEntity<List<OrderEntity>> getByDate(){
        return ResponseEntity.ok(this.orderService.getByDate());
    }

    @RequestMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @RequestMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getByCustomer(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getByCustomerId(id));
    }

    @GetMapping("/summary/{orderId}")
    private ResponseEntity<OrderSummary> getSummary(@PathVariable int orderId){
        return ResponseEntity.ok(this.orderService.getSummary(orderId));
    }

    @PostMapping("/random")
    private ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto){
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}
