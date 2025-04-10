package com.e_commerce.grocery_mart.entity.keys;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyProductWeight implements Serializable {

    @Column(name = "product_id")
    int productId;

    @Column(name = "weight_id")
    int weightId;
}
