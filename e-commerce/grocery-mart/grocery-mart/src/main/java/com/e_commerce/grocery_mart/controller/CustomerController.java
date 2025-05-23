package com.e_commerce.grocery_mart.controller;

import com.e_commerce.grocery_mart.dto.request.AddToCartRequest;
import com.e_commerce.grocery_mart.dto.request.UpdateCartItemRequest;
import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import com.e_commerce.grocery_mart.dto.response.CartDTO;
import com.e_commerce.grocery_mart.dto.response.CartItemDTO;
import com.e_commerce.grocery_mart.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/customer")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerController {

    CustomerService customerService;

    @GetMapping("cart/{id}")
    ApiResponse<CartDTO> getCartByCustomerId(@PathVariable(name = "id") UUID customerId) {
        return ApiResponse.<CartDTO>builder()
                .result(customerService.getCart(customerId))
                .build();
    }

    @PostMapping("/cart")
    ApiResponse<Void> addToCart(@RequestBody AddToCartRequest request) {
        customerService.addToCart(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PutMapping("/cart")
    ApiResponse<Void> updateCart(@RequestBody UpdateCartItemRequest request) {
        customerService.updateCart(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/cart/{id}")
    ApiResponse<Void> deleteCartItem(@PathVariable int id) {
        customerService.removeFromCart(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
