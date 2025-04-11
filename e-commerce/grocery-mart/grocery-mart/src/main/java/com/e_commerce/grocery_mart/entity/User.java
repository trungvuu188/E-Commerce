package com.e_commerce.grocery_mart.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable=false, updatable=false)
    Role role;

    @OneToMany(mappedBy = "createPerson")
    Set<Product> createdProducts;

    @OneToMany(mappedBy = "modifyPerson")
    Set<Product> modifiedProducts;
}
