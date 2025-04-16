package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.ProductInventoryCreationRequest;
import com.e_commerce.grocery_mart.dto.request.ProductInventoryUpdateRequest;
import com.e_commerce.grocery_mart.dto.response.ProductInventoryDTO;
import com.e_commerce.grocery_mart.dto.response.WarehouseDTO;
import com.e_commerce.grocery_mart.entity.WarehouseInventory;

import java.util.List;

public interface WarehouseService {

    List<WarehouseDTO> getWarehouseByBrand(int brandId);
    void createWarehouse(int brandId, String warehouseName);
    void modifyWarehouse(int brandId, String warehouseName);
    void deleteWarehouse(int warehouseId);
    void addProductInventory(ProductInventoryCreationRequest request);
    List<ProductInventoryDTO> getProductInventory(int warehouseId);
    void updateProductInventory(ProductInventoryUpdateRequest request);
    void deleteProductInventory(int id);
    List<WarehouseInventory> getInventoryByProductId(int productId);
}
