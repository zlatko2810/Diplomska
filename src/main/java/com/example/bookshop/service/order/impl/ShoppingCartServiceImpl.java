package com.example.bookshop.service.order.impl;

import com.example.bookshop.entity.dto.ShoppingCartDto;
import com.example.bookshop.entity.order.ShoppingCartEntity;
import com.example.bookshop.entity.product.ProductEntity;
import com.example.bookshop.entity.user.UserEntity;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.repository.ShoppingCartRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.order.ShoppingCartService;
import com.example.bookshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    @Override
    public void addProductToShoppingCart(Long productId, String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        ProductEntity product = productRepository.findById(productId).orElseThrow(Exception::new);
        ShoppingCartEntity shoppingCart = user.getShoppingCart();
        shoppingCart.getProducts().add(product);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteProductFromShoppingCart(Long productId, String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        ProductEntity product = productRepository.findById(productId).orElseThrow(Exception::new);
        ShoppingCartEntity shoppingCart = user.getShoppingCart();
        shoppingCart.getProducts().remove(product);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDto getUserShoppingCart(String email) throws Exception {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(Exception::new);
        ShoppingCartEntity shoppingCart = user.getShoppingCart();
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(shoppingCart.getId());
        shoppingCartDto.setProducts(new HashSet<>());
        for (ProductEntity p : shoppingCart.getProducts()) {
            shoppingCartDto.getProducts().add(productService.findById(p.getId()));
        }
        return shoppingCartDto;
    }
}
