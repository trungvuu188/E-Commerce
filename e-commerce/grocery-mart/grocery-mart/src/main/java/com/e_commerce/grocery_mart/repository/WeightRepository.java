package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Integer> {
}
