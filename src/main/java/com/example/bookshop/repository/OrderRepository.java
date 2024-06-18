package com.example.bookshop.repository;

import com.example.bookshop.entity.order.OrderEntity;
import com.example.bookshop.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUser(UserEntity user);
}
