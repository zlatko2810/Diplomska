package com.example.bookshop.service.order.impl;

import com.example.bookshop.entity.dto.OrderDto;
import com.example.bookshop.entity.order.OrderDetailEntity;
import com.example.bookshop.entity.order.OrderEntity;
import com.example.bookshop.entity.order.ShoppingCartEntity;
import com.example.bookshop.entity.product.ProductEntity;
import com.example.bookshop.entity.user.UserEntity;
import com.example.bookshop.repository.*;
import com.example.bookshop.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<OrderDto> getUserOrders(String email) throws Exception {

        if (email == null) {
            List<OrderEntity> orders = orderRepository.findAll();
            List<OrderDto> userOrders = new ArrayList<>();
            for (OrderEntity order : orders) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderId(order.getId());
                orderDto.setDate(order.getDate());
                orderDto.setPrice(order.getPrice());
                orderDto.setEmail(order.getUser().getEmail());
                orderDto.setStatus(order.getStatus());
                Map<Long, Integer> orderDetails = new HashMap<>();
                for (OrderDetailEntity orderDetailEntity : order.getOrderDetails()) {
                    orderDetails.put(orderDetailEntity.getOrder().getId(), orderDetailEntity.getQuantity());
                }
                orderDto.setOrderDetails(orderDetails);
                userOrders.add(orderDto);
            }
            return userOrders;
        }

        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        List<OrderEntity> orders = orderRepository.findAllByUser(user);
        List<OrderDto> userOrders = new ArrayList<>();
        for (OrderEntity order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getId());
            orderDto.setDate(order.getDate());
            orderDto.setPrice(order.getPrice());
            orderDto.setEmail(user.getEmail());
            orderDto.setStatus(order.getStatus());
            Map<Long, Integer> orderDetails = new HashMap<>();
            for (OrderDetailEntity orderDetailEntity : order.getOrderDetails()) {
                orderDetails.put(orderDetailEntity.getOrder().getId(), orderDetailEntity.getQuantity());
            }
            orderDto.setOrderDetails(orderDetails);
            userOrders.add(orderDto);
        }

        return userOrders;
    }

    @Override
    public void createOrder(String email, Map<Long, Integer> orderDetails) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        int totalPrice = calculateTotalPrice(orderDetails);
        ShoppingCartEntity shoppingCart = user.getShoppingCart();

        OrderEntity order = new OrderEntity();
        order.setOrderDetails(new ArrayList<>());
        orderRepository.save(order);

        for (ProductEntity product : shoppingCart.getProducts()) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrder(order);
            orderDetailEntity.setProduct(product);
            orderDetailEntity.setQuantity(orderDetails.get(product.getId()));
            orderDetailRepository.save(orderDetailEntity);
            order.getOrderDetails().add(orderDetailEntity);
        }

        order.setPrice(totalPrice);
        order.setDate(LocalDate.now());
        order.setStatus("ACTIVE");
        order.setUser(user);
        orderRepository.save(order);

        shoppingCart.getProducts().clear();
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public int calculateTotalPrice(Map<Long, Integer> orderDetails) {
        int totalPrice = 0;
        for (Long id : orderDetails.keySet()) {
            totalPrice += (productRepository.findById(id).get().getPrice()) * (orderDetails.get(id));
        }
        return totalPrice;
    }

}
