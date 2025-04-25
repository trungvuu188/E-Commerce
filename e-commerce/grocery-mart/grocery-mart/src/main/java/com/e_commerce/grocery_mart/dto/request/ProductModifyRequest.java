package com.e_commerce.grocery_mart.dto.request;

import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModifyRequest {

    UUID modifyPersonId;
    String productName;
    String productDesc;
    double basePrice;
    String imageUrl;
}
