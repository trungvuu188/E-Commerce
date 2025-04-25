package com.e_commerce.grocery_mart.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingProductRequest {

    UUID customerId;
    int productId;
    int stars;
    String comment;
}
