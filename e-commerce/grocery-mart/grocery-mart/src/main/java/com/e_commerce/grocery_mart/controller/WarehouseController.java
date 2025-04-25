package com.e_commerce.grocery_mart.controller;

import com.e_commerce.grocery_mart.dto.request.ProductInventoryCreationRequest;
import com.e_commerce.grocery_mart.dto.request.ProductInventoryUpdateRequest;
import com.e_commerce.grocery_mart.dto.request.WarehouseCreationRequest;
import com.e_commerce.grocery_mart.dto.request.WarehouseModifyRequest;
import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import com.e_commerce.grocery_mart.dto.response.ProductInventoryDTO;
import com.e_commerce.grocery_mart.dto.response.WarehouseDTO;
import com.e_commerce.grocery_mart.service.WarehouseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/warehouse")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WarehouseController {

    WarehouseService warehouseService;

    @GetMapping("/{id}")
    ApiResponse<List<WarehouseDTO>> getWarehouseByBrandId(@PathVariable(name = "id") int brandId) {
        return ApiResponse.<List<WarehouseDTO>>builder()
                .result(warehouseService.getWarehouseByBrand(brandId))
                .build();
    }

    @GetMapping("/inventory/{id}")
    ApiResponse<List<ProductInventoryDTO>> getProductInventory(@PathVariable(name = "id") int warehouseId) {
        return ApiResponse.<List<ProductInventoryDTO>>builder()
                .result(warehouseService.getProductInventory(warehouseId))
                .build();
    }

    @PostMapping
    ApiResponse<Void> createWarehouse(@RequestBody WarehouseCreationRequest request) {
        warehouseService.createWarehouse(request.getBrandId(), request.getWarehouseName());
        return ApiResponse.<Void>builder()
                .build();
    }

    @PatchMapping
    ApiResponse<Void> modifyWarehouse(@RequestBody WarehouseModifyRequest request) {
        warehouseService.modifyWarehouse(request.getWarehouseId(), request.getWarehouseName());
        return ApiResponse.<Void>builder()
                .build();
    }

    @PatchMapping("/inventory")
    ApiResponse<Void> updateProductInventory(@RequestBody ProductInventoryUpdateRequest request) {
        warehouseService.updateProductInventory(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteWarehouse(@PathVariable(name = "id") int warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/inventory/{id}")
    ApiResponse<Void> deleteProductInventory(@PathVariable(name = "id") int productInventoryId) {
        warehouseService.deleteProductInventory(productInventoryId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
