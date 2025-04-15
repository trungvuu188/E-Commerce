package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);
    @Modifying
    @Query("DELETE FROM roles r where r.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
