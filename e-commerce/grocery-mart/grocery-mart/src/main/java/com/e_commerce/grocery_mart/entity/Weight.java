package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "weights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "weight_name")
    String weightName;

    @OneToMany(mappedBy = "weight")
    List<WarehouseInventory> warehouseInventories;

    @OneToMany(mappedBy = "weight")
    List<ProductWeight> productWeights;

    @OneToMany(mappedBy = "weight")
    List<CartItem> cartItems;

    @OneToMany(mappedBy = "weight")
    List<OrderItem> orderItems;
}
