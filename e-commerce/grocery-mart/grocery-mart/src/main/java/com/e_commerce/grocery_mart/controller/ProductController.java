package com.e_commerce.grocery_mart.controller;

import com.e_commerce.grocery_mart.dto.request.ProductCreationRequest;
import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import com.e_commerce.grocery_mart.dto.response.ProductDTO;
import com.e_commerce.grocery_mart.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {

    ProductService productService;

    @GetMapping
    ApiResponse<List<ProductDTO>> getAllProducts(){
        return ApiResponse.<List<ProductDTO>>builder()
                .result(productService.getAllProduct())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductDTO> getProductById(@PathVariable int id){
        return ApiResponse.<ProductDTO>builder()
                .result(productService.getProductById(id))
                .build();
    }

    @PostMapping
    ApiResponse<Void> createProduct(@RequestBody ProductCreationRequest request){
        productService.addProduct(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PutMapping
    ApiResponse<Void> modifyProduct(){
        productService.modifyProduct();
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
