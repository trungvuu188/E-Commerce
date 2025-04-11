package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "sizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "size_name")
    String sizeName;

    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    List<WarehouseInventory> warehouseInventories;

    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    List<ProductSize> productSizes;

    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    List<CartItem> cartItems;

    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    List<OrderItem> orderItems;
}
