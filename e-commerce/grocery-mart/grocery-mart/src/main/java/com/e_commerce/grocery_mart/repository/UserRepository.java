package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String userName);
    User findByUsername(String userName);
    @Modifying
    @Query("DELETE FROM users u where u.id = :id")
    int deleteAndCountById(@Param("id") UUID id);
}
