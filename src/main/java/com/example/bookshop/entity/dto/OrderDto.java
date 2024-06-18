package com.example.bookshop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private String status;
    private LocalDate date;
    private int price;
    private String email;
    private Map<Long, Integer> orderDetails;
}
