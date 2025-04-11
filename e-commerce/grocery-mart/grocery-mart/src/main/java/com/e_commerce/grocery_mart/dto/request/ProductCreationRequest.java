package com.e_commerce.grocery_mart.dto.request;

import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.e_commerce.grocery_mart.dto.response.ProductWeightDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {

    int brandId;
    int createPersonId;
    String productName;
    String productDesc;
    double basePrice;
    String imageUrl;
    List<ProductSizeDTO> productSizeDTOS;
    List<ProductWeightDTO> productWeightDTOS;
}
