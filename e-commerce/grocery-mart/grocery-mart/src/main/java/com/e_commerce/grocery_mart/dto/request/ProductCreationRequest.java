package com.e_commerce.grocery_mart.dto.request;

import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {

    int brandId;
    int warehouseId;
    int createPersonId;
    String productName;
    String productDesc;
    double basePrice;
    MultipartFile imageUrl;
    List<ProductSizeDTO> productSizeDTOS;
}
