package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByCustomerId(UUID customerId);
}
