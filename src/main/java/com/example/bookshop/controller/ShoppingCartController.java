package com.example.bookshop.controller;

import com.example.bookshop.entity.dto.ShoppingCartDto;
import com.example.bookshop.entity.order.ShoppingCartEntity;
import com.example.bookshop.service.order.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;


    /**
     * Method to get user shopping cart
     *
     * @return Shopping Cart Entity
     */
    @GetMapping
    public ResponseEntity<ShoppingCartDto> getShoppingCartByUser(@RequestParam(name = "email") String email) throws Exception {
        return ResponseEntity.ok(shoppingCartService.getUserShoppingCart(email));
    }

    /**
     * Method to add product to shopping cart
     *
     * @return response status
     */
    @PostMapping("/add")
    public ResponseEntity<String> addProductToShoppingCart(@RequestParam(name = "productId") Long productId,
                                                           @RequestParam(name = "email") String email) throws Exception {
        shoppingCartService.addProductToShoppingCart(productId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully added to shopping cart");
    }

    /**
     * Method to delete product from shopping cart
     *
     * @return response status
     */
    @PostMapping("/delete")
    public ResponseEntity<String> deleteProductFromShoppingCart(@RequestParam(name = "productId") Long productId,
                                                                @RequestParam(name = "email") String email) throws Exception {
        shoppingCartService.deleteProductFromShoppingCart(productId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully deleted from shopping cart");
    }


}
