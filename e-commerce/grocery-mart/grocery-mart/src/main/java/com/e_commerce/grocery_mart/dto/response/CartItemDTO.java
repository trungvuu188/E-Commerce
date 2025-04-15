package com.e_commerce.grocery_mart.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDTO {

    int cartItemId;
    int productId;
    String brandName;
    String productName;
    String imageUrl;
    int sizeId;
    String sizeName;
    int weightId;
    String weightName;
    int quantity;
    double price;
}
