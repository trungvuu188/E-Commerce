package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Customer extends User {

    @Column(name = "full_name")
    String fullName;

    @Column(name = "email")
    @Email
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "avatar")
    String avatarUrl;

    @OneToMany(mappedBy = "customer")
    Set<Address> addressSet;

    @OneToOne(mappedBy = "customer")
    Cart cart;

    @OneToMany(mappedBy = "customer")
    List<Order> orderList;
}
