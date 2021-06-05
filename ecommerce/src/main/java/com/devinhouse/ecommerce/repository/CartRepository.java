package com.devinhouse.ecommerce.repository;

import com.devinhouse.ecommerce.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}
