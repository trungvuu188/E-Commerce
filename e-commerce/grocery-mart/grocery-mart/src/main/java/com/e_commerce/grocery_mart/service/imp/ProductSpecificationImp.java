package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.entity.*;
import com.e_commerce.grocery_mart.service.ProductSpecification;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationImp implements ProductSpecification {
    @Override
    public Specification<Product> brandLike(int brandId) {
        return ((root, query, builder) -> builder.equal(root.get("brand").as(Integer.class), brandId));
    }

    @Override
    public Specification<Product> brandNameLike(String brandName) {
        return ((root, query, builder) -> {
            Join<Product, Brand> brandJoin = root.join("brand");
            return builder.like(brandJoin.get("brandName"), "%" + brandName + "%");
        });
    }

    @Override
    public Specification<Product> productNameLike(String productName) {
        return ((root, query, builder) -> builder.like(root.get("productName"), "%" + productName + "%"));
    }
}
