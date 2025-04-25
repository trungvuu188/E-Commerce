package com.e_commerce.grocery_mart.mapper;

import com.e_commerce.grocery_mart.dto.response.ProductDTO;
import com.e_commerce.grocery_mart.dto.response.ProductInventoryDTO;
import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.e_commerce.grocery_mart.entity.Product;
import com.e_commerce.grocery_mart.entity.ProductSize;
import com.e_commerce.grocery_mart.entity.Rating;
import com.e_commerce.grocery_mart.entity.WarehouseInventory;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {


    private final DecimalFormat df = new DecimalFormat("#.##");
    public ProductDTO toProductDTO(Product product) {

        List<ProductSizeDTO> productSizeDTOS = new ArrayList<>();

        for(WarehouseInventory warehouseInventory : product.getWarehouseInventories()) {
            double priceSizeScale = getPriceSizeScale(product.getProductSizes(), warehouseInventory.getSize().getId());
            productSizeDTOS.add(ProductSizeDTO.builder()
                            .sizeId(warehouseInventory.getSize().getId())
                            .sizeName(warehouseInventory.getSize().getSizeName())
                            .quantity(warehouseInventory.getQuantity())
                            .priceScale(Double.valueOf(df.format(product.getBasePrice() + product.getBasePrice() * priceSizeScale)))
                            .build());
        }

        return ProductDTO.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productDesc(product.getProductDesc())
                .brandId(product.getBrand().getId())
                .brandName(product.getBrand().getBrandName())
                .imageUrl(product.getImageURL())
                .productSizeDTOList(productSizeDTOS)
                .averageStar(calculateAverageRatingStars(product.getRatings()))
                .totalStars(product.getRatings().size())
                .build();
    }

    public ProductInventoryDTO toProductInventoryDTO(WarehouseInventory warehouseInventory) {

        return ProductInventoryDTO.builder()
//                .warehouseInventoryId(warehouseInventory.getId())
//                .productId(warehouseInventory.getProduct().getId())
//                .productName(warehouseInventory.getProduct().getProductName())
//                .sizeId(warehouseInventory.getSize().getId())
//                .sizeName(warehouseInventory.getSize().getSizeName())
//                .quantity(warehouseInventory.getQuantity())
                .build();
    }

    public double calculateAverageRatingStars(List<Rating> ratings) {
        double totalStars = 0;
        for (Rating rating : ratings) {
            totalStars += rating.getStars();
        }
        return totalStars / ratings.size();
    }

    public double getPriceSizeScale(List<ProductSize> productSizes, int sizeId) {
        return productSizes.stream().filter(productSize -> productSize.getSize().getId() == sizeId)
                .collect(Collectors.toList()).get(0).getPriceScale();
    }

}
