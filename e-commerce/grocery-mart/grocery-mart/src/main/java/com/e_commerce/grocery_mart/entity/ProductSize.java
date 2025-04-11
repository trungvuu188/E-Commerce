package com.e_commerce.grocery_mart.entity;

import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity(name = "product_size")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSize implements Serializable {

    @EmbeddedId
    KeyProductSize keyProductSize;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", insertable=false, updatable=false)
    Size size;

    @Column(name = "price_scale")
    double priceScale;

    public Product getProduct() {
        return keyProductSize.getProduct();
    }

    public void setProduct(Product product) {
        if (keyProductSize == null) {
            keyProductSize = new KeyProductSize();
        }
        keyProductSize.setProduct(product);
    }
}
