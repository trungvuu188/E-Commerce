package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.AddToCartRequest;
import com.e_commerce.grocery_mart.dto.request.RatingProductRequest;
import com.e_commerce.grocery_mart.dto.request.UpdateCartItemRequest;
import com.e_commerce.grocery_mart.dto.response.CartDTO;
import com.e_commerce.grocery_mart.dto.response.CartItemDTO;
import com.e_commerce.grocery_mart.entity.Cart;
import com.e_commerce.grocery_mart.entity.CartItem;
import com.e_commerce.grocery_mart.entity.Customer;
import com.e_commerce.grocery_mart.entity.Rating;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.helper.DateTimeConverter;
import com.e_commerce.grocery_mart.repository.CartItemRepository;
import com.e_commerce.grocery_mart.repository.CartRepository;
import com.e_commerce.grocery_mart.repository.CustomerRepository;
import com.e_commerce.grocery_mart.repository.RatingRepository;
import com.e_commerce.grocery_mart.service.CustomerService;
import com.e_commerce.grocery_mart.service.ProductService;
import com.e_commerce.grocery_mart.service.ProductSubService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    CustomerRepository customerRepository;
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;
    ProductService productService;
    ProductSubService productSubService;
    RatingRepository ratingRepository;
    DateTimeConverter dateTimeConverter;

    @Override
    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOTFOUND_EXCEPTION));
    }

    @Override
    public Cart getOrCreateCart(UUID customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if(cart == null) {
            cart = Cart.builder()
                    .customer(getCustomerById(customerId))
                    .build();
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public CartDTO getCart(UUID customerId) {
        Cart cart = getOrCreateCart(customerId);
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        if(cart.getCartItems() != null) {
            for (CartItem cartItem : cart.getCartItems()) {
                double itemPrice = productService.calculateProductPrice(
                        cartItem.getProduct().getId(),
                        cartItem.getSize().getId(),
                        cartItem.getQuantity()
                );
                CartItemDTO cartItemDTO = CartItemDTO.builder()
                        .cartItemId(cartItem.getId())
                        .productId(cartItem.getProduct().getId())
                        .brandName(cartItem.getProduct().getBrand().getBrandName())
                        .productName(cartItem.getProduct().getProductName())
                        .sizeId(cartItem.getSize().getId())
                        .sizeName(cartItem.getSize().getSizeName())
                        .quantity(cartItem.getQuantity())
                        .imageUrl(cartItem.getProduct().getImageURL())
                        .price(itemPrice)
                        .build();
                cartItemDTOS.add(cartItemDTO);
            }
        }
        return CartDTO.builder()
                .cartId(cart.getId())
                .cartItemDTOS(cartItemDTOS)
                .build();
    }

    @Override
    public void addToCart(AddToCartRequest request) {
        Cart cart = getOrCreateCart(request.getCustomerId());
        CartItem cartItem = cartItemRepository.findByCartIdAndProductIdAndSizeId(
                request.getCartId(),
                request.getProductId(),
                request.getSizeId());
        if(cartItem == null) {
            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(productService.getBaseProductById(request.getProductId()))
                    .size(productSubService.getSizeById(request.getSizeId()))
                    .quantity(request.getQuantity())
                    .build();
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        cartItem.setTotalPrice(
                productService.calculateProductPrice(
                        request.getProductId(), request.getSizeId(), cartItem.getQuantity()
                )
        );
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void updateCart(UpdateCartItemRequest request) {

        CartItem cartItem = cartItemRepository.findById(request.getCartItemId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOTFOUND_EXCEPTION));

        CartItem cartItemDuplicated = cartItemRepository.findByCartIdAndProductIdAndSizeId(
                request.getCartId(),
                cartItem.getProduct().getId(),
                request.getSizeId()
                );
        if(cartItemDuplicated == null) {
            if(request.getSizeId() != 0) {
                cartItem.setSize(productSubService.getSizeById(request.getSizeId()));
            }
            if(request.getQuantity() != 0){
                cartItem.setQuantity(request.getQuantity());
            }
            cartItemRepository.save(cartItem);
        } else {
            cartItemDuplicated.setQuantity(request.getQuantity() + cartItemDuplicated.getQuantity());
            cartItemRepository.deleteById(cartItem.getId());
            cartItemRepository.save(cartItemDuplicated);
        }
    }

    @Override
    @Transactional
    public void removeFromCart(int cartItemId) {
        if(cartItemRepository.deleteAndGetCountById(cartItemId) == 0) {
            throw new AppException(ErrorCode.CART_ITEM_NOTFOUND_EXCEPTION);
        }
    }

    @Override
    public void addToWishlist() {

    }

    @Override
    public void updateWishlist() {

    }

    @Override
    public void deleteFromWishlist(int wishlistId) {

    }

    @Override
    public void ratingProduct(RatingProductRequest request) {
        Rating rating = Rating.builder()
                .customer(getCustomerById(request.getCustomerId()))
                .product(productService.getBaseProductById(request.getProductId()))
                .stars(request.getStars())
                .comment(request.getComment())
                .createAt(dateTimeConverter.formatDate(LocalDate.now()))
                .build();
        ratingRepository.save(rating);
    }
}
