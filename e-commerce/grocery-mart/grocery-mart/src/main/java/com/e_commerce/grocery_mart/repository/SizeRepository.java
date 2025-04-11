package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    boolean existsBySizeName(String sizeName);

    @Modifying
    @Query("DELETE FROM sizes s WHERE s.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
