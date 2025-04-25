package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByCartIdAndProductIdAndSizeId(int cartId, int productId, int sizeId);
    @Modifying
    @Query("DELETE FROM cart_item c where c.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
