package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse findByBrand_Id(int brandId);
    List<Warehouse> findAllByBrand_Id(int brandId);
    boolean existsByWareHouseName(String warehouseName);
    @Modifying
    @Query("DELETE FROM warehouses w where w.id = :id")
    int deleteAndGetCountById(@Param("id") int id);
}
