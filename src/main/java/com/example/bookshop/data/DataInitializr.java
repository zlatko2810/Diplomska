package com.example.bookshop.data;

import com.example.bookshop.entity.order.ShoppingCartEntity;
import com.example.bookshop.entity.user.UserEntity;
import com.example.bookshop.entity.user.UserRole;
import com.example.bookshop.repository.ShoppingCartRepository;
import com.example.bookshop.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DataInitializr {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ShoppingCartRepository shoppingCartRepository;

    @PostConstruct
    public void addData() {

        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail("admin@gmail.com");
        if (optionalUserEntity.isEmpty()) {
            ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
            shoppingCartRepository.save(shoppingCart);
            UserEntity user = new UserEntity("admin", "admin", "0", "admin", "admin@gmail.com", passwordEncoder.encode("admin"), UserRole.ADMIN, shoppingCart);
            userRepository.save(user);
        }

    }
}
