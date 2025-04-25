package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.ProductCreationRequest;
import com.e_commerce.grocery_mart.dto.request.ProductModifyRequest;
import com.e_commerce.grocery_mart.dto.response.ProductDTO;
import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.e_commerce.grocery_mart.entity.Product;
import com.e_commerce.grocery_mart.entity.Rating;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllFeatureProduct();
    List<ProductDTO> getAllProduct(Integer brandId, String brandName, String productName);
    ProductDTO getProductById(int productId);
    Product getBaseProductById(int productId);
    Product addProduct(ProductCreationRequest request);
    void modifyProduct(int productId, ProductModifyRequest request);
    void deleteProduct(int id);
    void updateProductSize(Product product, List<ProductSizeDTO> productSizeDTOS);
    double calculateProductPrice(int productId, int sizeId, int quantity);
}
