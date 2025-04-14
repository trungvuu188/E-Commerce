package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.ProductSize;
import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, KeyProductSize>, JpaSpecificationExecutor<ProductSize> {
}
