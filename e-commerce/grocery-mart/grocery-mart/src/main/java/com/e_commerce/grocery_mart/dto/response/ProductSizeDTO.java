package com.e_commerce.grocery_mart.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeDTO {

    int sizeId;
    String sizeName;
    double priceScale;
    int quantity;
}
