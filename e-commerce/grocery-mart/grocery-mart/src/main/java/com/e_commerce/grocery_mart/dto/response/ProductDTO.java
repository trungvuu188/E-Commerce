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
public class ProductDTO {

    int productId;
    int brandId;
    String brandName;
    String productName;
    String productDesc;
    String imageUrl;
    List<ProductSizeDTO> productSizeDTOList;
    double averageStar;
    int totalStars;
}
