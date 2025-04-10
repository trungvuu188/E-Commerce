package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String userName);
    User findByUsernameAndPassword(String userName, String password);
}
