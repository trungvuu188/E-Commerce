package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    @EntityGraph(attributePaths = {"productSizes", "productWeights"})
    List<Product> findAll();

    boolean existsByProductName(String productName);

    @Modifying
    @Query("DELETE FROM products p WHERE p.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
