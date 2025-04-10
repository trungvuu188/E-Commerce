package com.e_commerce.grocery_mart.entity;

import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import com.e_commerce.grocery_mart.entity.keys.KeyProductWeight;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "product_weight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductWeight {

    @EmbeddedId
    KeyProductWeight keyProductWeight;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "weight_id", referencedColumnName = "id")
    Weight weight;

    @Column(name = "price_scale")
    double priceScale;
}
