package com.example.bookshop.service.order;

import lombok.Getter;

import java.util.Map;

@Getter
public class OrderRequest {

    private String email;

    private Map<Long, Integer> orderDetails;
}
