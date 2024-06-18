package com.example.bookshop.service.order;

import com.example.bookshop.entity.dto.ShoppingCartDto;
import com.example.bookshop.entity.order.ShoppingCartEntity;

public interface ShoppingCartService {

    void addProductToShoppingCart(Long productId, String email) throws Exception;

    void deleteProductFromShoppingCart(Long productId, String email) throws Exception;

    ShoppingCartDto getUserShoppingCart(String email) throws Exception;
}
