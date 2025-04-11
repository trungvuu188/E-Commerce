package com.e_commerce.grocery_mart.mapper;

import com.e_commerce.grocery_mart.dto.response.ProductDTO;
import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.e_commerce.grocery_mart.dto.response.ProductWeightDTO;
import com.e_commerce.grocery_mart.entity.Product;
import com.e_commerce.grocery_mart.entity.ProductSize;
import com.e_commerce.grocery_mart.entity.ProductWeight;
import com.e_commerce.grocery_mart.entity.Size;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public ProductDTO toProductDTO(Product product) {

        List<ProductSizeDTO> productSizeDTOS = new ArrayList<>();
        List<ProductWeightDTO> productWeightDTOS = new ArrayList<>();

//        Loop list size of product
        for(ProductSize productSize : product.getProductSizes()) {
            productSizeDTOS.add(ProductSizeDTO.builder()
                            .sizeId(productSize.getSize().getId())
                            .sizeName(productSize.getSize().getSizeName())
                            .priceScale(productSize.getPriceScale())
                            .build());
        }
//        Loop list weight of product
        for(ProductWeight productWeight : product.getProductWeights()) {
            productWeightDTOS.add(ProductWeightDTO.builder()
                    .weightId(productWeight.getWeight().getId())
                    .weightName(productWeight.getWeight().getWeightName())
                    .priceScale(productWeight.getPriceScale())
                    .build());
        }

        return ProductDTO.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productDesc(product.getProductDesc())
                .brandName(product.getBrand().getBrandName())
                .imageUrl(product.getImageURL())
                .productSizeDTOList(productSizeDTOS)
                .productWeightDTOList(productWeightDTOS)
                .basePrice(product.getBasePrice())
                .build();
    }

}
