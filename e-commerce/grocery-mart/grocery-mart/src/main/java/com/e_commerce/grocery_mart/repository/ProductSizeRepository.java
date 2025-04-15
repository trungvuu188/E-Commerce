package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.ProductSize;
import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, KeyProductSize>, JpaSpecificationExecutor<ProductSize> {
    ProductSize findByKeyProductSize(KeyProductSize keyProductSize);

//    @Query("SELECT ps FROM product_size ps WHERE ps.priceScale = (SELECT MIN(ps2.priceScale) FROM product_size ps2)")
//    Optional<ProductSize> findProductSizeWithMinPriceScale();
}
