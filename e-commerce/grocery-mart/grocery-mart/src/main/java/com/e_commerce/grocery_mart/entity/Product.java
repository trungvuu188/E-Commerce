package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Date createdAt;

    @Column(name = "modified_at")
    Date modifiedAt;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    Brand brand;

    @ManyToOne
    @JoinColumn(name = "create_person_id", referencedColumnName = "id")
    User createPerson;

    @ManyToOne
    @JoinColumn(name = "modify_person_id", referencedColumnName = "id")
    User modifyPerson;

    @OneToMany(mappedBy = "product")
    List<CartItem> cartItemList;

    @OneToMany(mappedBy = "product")
    List<OrderItem> orderItems;
}
