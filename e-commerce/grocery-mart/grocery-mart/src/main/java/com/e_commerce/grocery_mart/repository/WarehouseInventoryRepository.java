package com.e_commerce.grocery_mart.repository;

import com.e_commerce.grocery_mart.entity.WarehouseInventory;
import com.e_commerce.grocery_mart.entity.keys.KeyWarehouseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory, KeyWarehouseProduct> {

}
