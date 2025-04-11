package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable=false, updatable=false)
    Order order;

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
