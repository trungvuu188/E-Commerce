package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.ProductCreationRequest;
import com.e_commerce.grocery_mart.dto.response.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProduct();
    ProductDTO getProductById(int productId);
    void addProduct(ProductCreationRequest request);
    void modifyProduct();
    void deleteProduct(int id);
}
