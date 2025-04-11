package com.e_commerce.grocery_mart.entity;

import com.e_commerce.grocery_mart.entity.keys.KeyProductWeight;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity(name = "product_weight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductWeight implements Serializable {

    @EmbeddedId
    KeyProductWeight keyProductWeight;

    @ManyToOne
    @JoinColumn(name = "weight_id", referencedColumnName = "id", insertable=false, updatable=false)
    Weight weight;

    @Column(name = "price_scale")
    double priceScale;

    public Product getProduct() {
        return keyProductWeight.getProduct();
    }

    public void setProduct(Product product) {
        if (keyProductWeight == null) {
            keyProductWeight = new KeyProductWeight();
        }
        keyProductWeight.setProduct(product);
    }
}
