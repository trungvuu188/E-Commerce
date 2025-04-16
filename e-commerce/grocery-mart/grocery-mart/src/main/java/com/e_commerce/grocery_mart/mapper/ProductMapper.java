package com.e_commerce.grocery_mart.mapper;

import com.e_commerce.grocery_mart.dto.response.ProductDTO;
import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.e_commerce.grocery_mart.entity.Product;
import com.e_commerce.grocery_mart.entity.ProductSize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductDTO toProductDTO(Product product) {

        List<ProductSizeDTO> productSizeDTOS = new ArrayList<>();

        for(ProductSize productSize : product.getProductSizes()) {
            productSizeDTOS.add(ProductSizeDTO.builder()
                            .sizeId(productSize.getSize().getId())
                            .sizeName(productSize.getSize().getSizeName())
                            .priceScale(productSize.getPriceScale())
                            .build());
        }

        // Find smallest priceScale
        double smallestPriceScale = product.getProductSizes().stream()
                .mapToDouble(ProductSize::getPriceScale)
                .min()
                .orElse(0.0);

        double finalPrice = product.getBasePrice() + (product.getBasePrice() * smallestPriceScale);

        return ProductDTO.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productDesc(product.getProductDesc())
                .brandName(product.getBrand().getBrandName())
                .imageUrl(product.getImageURL())
                .productSizeDTOList(productSizeDTOS)
                .basePrice(product.getBasePrice())
                .calculatedPrice(finalPrice)
                .build();
    }

}
