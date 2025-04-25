package com.e_commerce.grocery_mart.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandProductDTO {

    int warehouseId;
    String warehouseName;
    int brandId;
    String brandName;
    List<ProductInventoryDTO> productInventoryDTOList;
}
