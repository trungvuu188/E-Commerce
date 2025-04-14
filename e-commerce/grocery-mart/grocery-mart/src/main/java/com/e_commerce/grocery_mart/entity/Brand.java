package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "brand_name")
    String brandName;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    List<Warehouse> warehouseList;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    Set<Product> productSet;
}
