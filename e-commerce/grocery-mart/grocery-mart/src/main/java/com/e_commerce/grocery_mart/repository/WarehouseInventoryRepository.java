package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.WarehouseInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory, Integer> {

    boolean existsByProductIdAndSizeId(int productId, int sizeId);

    @Modifying
    @Query("DELETE FROM warehouse_inventory w where w.id = :id")
    int deleteAndGetCountById(int id);

    List<WarehouseInventory> findAllByProductId(int productId);
}
