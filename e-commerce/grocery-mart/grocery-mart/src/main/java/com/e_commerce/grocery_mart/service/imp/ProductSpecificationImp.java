package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.entity.Brand;
import com.e_commerce.grocery_mart.entity.Product;
import com.e_commerce.grocery_mart.entity.ProductSize;
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
            Join<Product, Brand> productBrandJoin = root.join("brand");
            return builder.like(productBrandJoin.get("brandName"), "%" + brandName + "%");
        });
    }

    @Override
    public Specification<Product> sizeLike(int sizeId) {
        return ((root, query, builder) -> {
            Join<Product, ProductSize> productProductSizeJoin = root.join("productSizes");
            return builder.equal(productProductSizeJoin.get("size").as(Integer.class), sizeId);
        });
    }
}
