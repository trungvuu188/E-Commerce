package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", insertable=false, updatable=false)
    Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable=false, updatable=false)
    Product product;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", insertable=false, updatable=false)
    Size size;

    @ManyToOne
    @JoinColumn(name = "weight_id", referencedColumnName = "id", insertable=false, updatable=false)
    Weight weight;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "total_price")
    double totalPrice;
}
