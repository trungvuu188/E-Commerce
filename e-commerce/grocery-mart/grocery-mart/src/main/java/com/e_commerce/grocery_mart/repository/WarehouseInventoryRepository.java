package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.WarehouseInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory, Integer> {

    boolean existsByProductIdAndSizeIdAndWeightId(int productId, int sizeId, int weightId);

    @Modifying
    @Query("DELETE FROM warehouse_inventory w where w.id = :id")
    int deleteAndGetCountById(int id);
}
