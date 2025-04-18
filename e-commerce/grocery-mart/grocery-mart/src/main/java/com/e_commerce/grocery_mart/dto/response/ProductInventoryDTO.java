package com.e_commerce.grocery_mart.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInventoryDTO {

    int warehouseInventoryId;
    int productId;
    String productName;
    int sizeId;
    String sizeName;
    int weightId;
    String weightName;
    int quantity;
}
