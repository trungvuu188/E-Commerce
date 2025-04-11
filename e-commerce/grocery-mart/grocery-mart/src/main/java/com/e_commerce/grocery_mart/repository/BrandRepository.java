package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByBrandName(String brandName);

    @Modifying
    @Query("DELETE FROM brands b WHERE b.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
