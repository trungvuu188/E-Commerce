package com.e_commerce.grocery_mart.entity;

import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "product_size")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSize {

    @EmbeddedId
    KeyProductSize keyProductSize;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    Size size;

    @Column(name = "price_scale")
    double priceScale;
}
