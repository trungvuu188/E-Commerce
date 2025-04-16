package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.AddToCartRequest;
import com.e_commerce.grocery_mart.dto.request.UpdateCartItemRequest;
import com.e_commerce.grocery_mart.dto.response.CartDTO;
import com.e_commerce.grocery_mart.dto.response.CartItemDTO;
import com.e_commerce.grocery_mart.entity.Cart;
import com.e_commerce.grocery_mart.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomerById(UUID customerId);
    Cart getOrCreateCart(UUID customerId);
    CartDTO getCart(UUID customerId);
    void addToCart(AddToCartRequest request);
    void updateCart(UpdateCartItemRequest request);
    void removeFromCart(int cartItemId);
    void addToWishlist();
    void updateWishlist();
    void deleteFromWishlist(int wishlistId);

}
