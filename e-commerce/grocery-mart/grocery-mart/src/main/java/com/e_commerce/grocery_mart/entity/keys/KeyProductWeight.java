package com.e_commerce.grocery_mart.entity.keys;

import com.e_commerce.grocery_mart.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @Column(name = "weight_id")
    int weightId;
}
