package com.devinhouse.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.devinhouse.ecommerce.dto.CartDTO;
import com.devinhouse.ecommerce.entity.Cart;
import com.devinhouse.ecommerce.entity.Product;
import com.devinhouse.ecommerce.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public Cart create() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart getById(Integer id) throws Exception {
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart patch(Cart cart) throws Exception {
        Cart databaseCart = cartRepository.findById(cart.getId()).orElseThrow();
        List<Product> products = new ArrayList<Product>();
        cart.getProducts().forEach(product -> products.add(product));
        databaseCart.setProducts(products);
        return cartRepository.save(databaseCart);
    }

    public CartDTO checkoutCartById(Integer id) throws Exception {
        Cart cart = cartRepository.findById(id).orElseThrow();
        CartDTO cartDTO = new CartDTO(cart);
        return cartDTO;
    }

}
