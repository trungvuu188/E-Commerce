package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Integer> {

    boolean existsByWeightName(String weightName);

    @Modifying
    @Query("DELETE FROM weights w WHERE w.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
