package com.devinhouse.ecommerce.controller;

import com.devinhouse.ecommerce.dto.CartDTO;
import com.devinhouse.ecommerce.entity.Cart;
import com.devinhouse.ecommerce.service.CartService;
import com.devinhouse.ecommerce.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/carts")
public class CartController {
    
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCartById(@PathVariable Integer id) {
        Cart cart;
        try {
            cart = cartService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCart() {
        Cart cart = cartService.create();
        return ResponseEntity.status(HttpStatus.OK).body(new CartDTO(cart));
    }

    @PatchMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> patchCart(@RequestBody CartDTO cartDTO) {
        Cart cart;
        try {
            cart = cartService.patch(cartDTO.toEntity());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CartDTO(cart));
    }

    @GetMapping(value = "checkout/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkoutCartById(@PathVariable Integer id) {
        CartDTO checkout;
        try {
            checkout = cartService.checkoutCartById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(checkout);
    }

}
