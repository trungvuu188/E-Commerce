package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.ProductWeight;
import com.e_commerce.grocery_mart.entity.keys.KeyProductWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWeightRepository extends JpaRepository<ProductWeight, KeyProductWeight>, JpaSpecificationExecutor<ProductWeight> {
    ProductWeight findByKeyProductWeight(KeyProductWeight keyProductWeight);
}
