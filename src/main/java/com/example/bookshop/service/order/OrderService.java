package com.example.bookshop.service.order;

import com.example.bookshop.entity.dto.OrderDto;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderDto> getUserOrders(String email) throws Exception;

    void createOrder(String email, Map<Long, Integer> orderDetails) throws Exception;

    void deleteOrder(Long orderId);
}
