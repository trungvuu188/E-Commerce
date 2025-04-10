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

    @OneToMany(mappedBy = "size")
    List<WarehouseInventory> warehouseInventories;

    @OneToMany(mappedBy = "size")
    List<ProductSize> productSizes;

    @OneToMany(mappedBy = "size")
    List<CartItem> cartItems;

    @OneToMany(mappedBy = "size")
    List<OrderItem> orderItems;
}
