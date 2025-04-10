package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByUsername(String userName);
}
