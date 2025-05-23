package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.ProductInventoryUpdateRequest;
import com.e_commerce.grocery_mart.dto.response.ProductInventoryDTO;
import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.e_commerce.grocery_mart.dto.response.WarehouseDTO;
import com.e_commerce.grocery_mart.entity.Product;
import com.e_commerce.grocery_mart.entity.Warehouse;
import com.e_commerce.grocery_mart.entity.WarehouseInventory;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.mapper.ProductMapper;
import com.e_commerce.grocery_mart.repository.WarehouseInventoryRepository;
import com.e_commerce.grocery_mart.repository.WarehouseRepository;
import com.e_commerce.grocery_mart.service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WarehouseServiceImp implements WarehouseService {

    WarehouseRepository warehouseRepository;
    WarehouseInventoryRepository warehouseInventoryRepository;
    ProductMapper productMapper;
    ProductSubService productSubService;
    BrandService brandService;
    @Override
    public List<WarehouseDTO> getWarehouseByBrand(int brandId) {
        List<Warehouse> warehouses = warehouseRepository.findAllByBrand_Id(brandId);
        if(warehouses.size() == 0) {
            throw new AppException(ErrorCode.WAREHOUSE_NOTFOUND_EXCEPTION);
        }
        List<WarehouseDTO> warehouseDTOS = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            WarehouseDTO warehouseDTO = WarehouseDTO.builder()
                    .warehouseId(warehouse.getId())
                    .warehouseName(warehouse.getWareHouseName())
                    .build();
            warehouseDTOS.add(warehouseDTO);
        }
        return warehouseDTOS;
    }

    @Override
    public void createWarehouse(int brandId, String warehouseName) {
        if(warehouseRepository.existsByWareHouseName(warehouseName)) {
            throw new AppException(ErrorCode.WAREHOUSE_EXISTED_EXCEPTION);
        }
        Warehouse warehouse = Warehouse.builder()
                .brand(brandService.getBrandById(brandId))
                .wareHouseName(warehouseName)
                .build();
        warehouseRepository.save(warehouse);
    }

    @Override
    public void modifyWarehouse(int warehouseId, String warehouseName) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOTFOUND_EXCEPTION));
        warehouse.setWareHouseName(warehouseName);
        warehouseRepository.save(warehouse);
    }

    @Override
    @Transactional
    public void deleteWarehouse(int warehouseId) {
        if(warehouseRepository.deleteAndGetCountById(warehouseId) == 0){
            throw new AppException(ErrorCode.WAREHOUSE_NOTFOUND_EXCEPTION);
        }
    }

    @Override
    public List<ProductInventoryDTO> getProductInventory(int warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOTFOUND_EXCEPTION));
        List<ProductInventoryDTO> productInventoryDTOS = new ArrayList<>();
        warehouse.getWarehouseInventories().forEach(warehouseInventory -> {
            ProductInventoryDTO productInventoryDTO = productMapper.toProductInventoryDTO(warehouseInventory);
            productInventoryDTOS.add(productInventoryDTO);
        });
        return productInventoryDTOS;
    }

    @Override
    public void addProductInventory(int warehouseId, Product product, List<ProductSizeDTO> productSizeDTOS) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new AppException(ErrorCode.WAREHOUSE_NOTFOUND_EXCEPTION));
        productSizeDTOS.forEach(productSizeDTO -> {
            if(warehouseInventoryRepository.existsByProductIdAndSizeId(product.getId(), productSizeDTO.getSizeId())){
                throw new AppException(ErrorCode.PRODUCT_INVENTORY_EXISTED_EXCEPTION);
            }
            WarehouseInventory warehouseInventory = WarehouseInventory.builder()
                    .warehouse(warehouse)
                    .product(product)
                    .size(productSubService.getSizeById(productSizeDTO.getSizeId()))
                    .quantity(productSizeDTO.getQuantity())
                    .build();
            warehouseInventoryRepository.save(warehouseInventory);
        });
    }

    @Override
    public void updateProductInventory(ProductInventoryUpdateRequest request) {
        WarehouseInventory warehouseInventory = warehouseInventoryRepository.findById(request.getWarehouseInventoryId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_INVENTORY_NOTFOUND_EXCEPTION));
        warehouseInventory.setQuantity(request.getQuantity());
        warehouseInventoryRepository.save(warehouseInventory);
    }

    @Override
    @Transactional
    public void deleteProductInventory(int id) {
        if(warehouseInventoryRepository.deleteAndGetCountById(id) == 0){
            throw new AppException(ErrorCode.PRODUCT_INVENTORY_NOTFOUND_EXCEPTION);
        }
    }

    @Override
    public List<WarehouseInventory> getInventoryByProductId(int productId) {
        return warehouseInventoryRepository.findAllByProductId(productId);
    }
}
