package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    boolean existsByProductName(String productName);

    @Modifying
    @Query("DELETE FROM products p WHERE p.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
