package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Product;
import com.e_commerce.grocery_mart.entity.ProductSize;
import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, KeyProductSize>, JpaSpecificationExecutor<ProductSize> {
    ProductSize findByKeyProductSize(KeyProductSize keyProductSize);
}
