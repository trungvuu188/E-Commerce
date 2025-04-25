package com.e_commerce.grocery_mart.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandUpdateRequest {

    int brandId;
    String brandName;
    MultipartFile brandImg;
}
