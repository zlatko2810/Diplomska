package com.example.bookshop.controller;

import com.example.bookshop.entity.dto.OrderDto;
import com.example.bookshop.service.order.OrderRequest;
import com.example.bookshop.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getUserOrders(@RequestParam(name = "email", required = false) String email) throws Exception {
        return ResponseEntity.ok(orderService.getUserOrders(email));
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        orderService.createOrder(orderRequest.getEmail(), orderRequest.getOrderDetails());
        return ResponseEntity.status(HttpStatus.CREATED).body("Order successfully created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order successfully deleted");
    }
}