package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public interface ProductSpecification {

    Specification<Product> brandLike(int brandId);
    Specification<Product> brandNameLike(String brandName);
    Specification<Product> sizeLike(int sizeId);
}
