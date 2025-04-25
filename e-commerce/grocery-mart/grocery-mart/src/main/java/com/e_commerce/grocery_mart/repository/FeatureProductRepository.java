package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.FeatureProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureProductRepository extends JpaRepository<FeatureProduct, Integer> {

    boolean existsByProductId(int productId);

    @Modifying
    @Query("DELETE FROM feature_products fp where fp.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
