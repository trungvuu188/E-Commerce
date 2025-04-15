package com.e_commerce.grocery_mart.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCartItemRequest {

    int cartId;
    int cartItemId;
    int sizeId;
    int weightId;
    int quantity;
}
