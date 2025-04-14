package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_desc")
    String productDesc;

    @Column(name = "base_price")
    double basePrice;

    @Column(name = "image")
    String imageURL;

    @Column(name = "is_out_of_stock")
    boolean isOutOfStock;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "modified_at")
    LocalDate modifiedAt;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_person_id", referencedColumnName = "id")
    User createPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_person_id", referencedColumnName = "id")
    User modifyPerson;

    @OneToMany(mappedBy = "keyProductSize.product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductSize> productSizes;

    @OneToMany(mappedBy = "keyProductWeight.product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductWeight> productWeights;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<CartItem> cartItemList;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<WarehouseInventory> warehouseInventories;


    //    Bidirectional (can set productSize without setting productId)
    public void addProductSize(ProductSize productSize) {
        productSizes.add(productSize);
        productSize.setProduct(this);
    }

//    Bidirectional
    public void addProductWeight(ProductWeight productWeight) {
        productWeights.add(productWeight);
        productWeight.setProduct(this);
    }
}
