package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.ProductCreationRequest;
import com.e_commerce.grocery_mart.dto.response.ProductDTO;
import com.e_commerce.grocery_mart.entity.*;
import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import com.e_commerce.grocery_mart.entity.keys.KeyProductWeight;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.helper.DateTimeConverter;
import com.e_commerce.grocery_mart.mapper.ProductMapper;
import com.e_commerce.grocery_mart.repository.*;
import com.e_commerce.grocery_mart.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    BrandRepository brandRepository;
    ProductRepository productRepository;
    ProductSizeRepository productSizeRepository;
    ProductWeightRepository productWeightRepository;
    SizeRepository sizeRepository;
    WeightRepository weightRepository;
    DateTimeConverter dateTimeConverter;
    ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProduct() {

        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for(Product product : products) {
            ProductDTO productDTO = productMapper.toProductDTO(product);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getProductById(int productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND_EXCEPTION));
        return productMapper.toProductDTO(product);
    }

    @Override
    @Transactional
    public void addProduct(ProductCreationRequest request) {

        if(productRepository.existsByProductName(request.getProductName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED_EXCEPTION);
        }
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOTFOUND_EXCEPTION));

        Product product = Product.builder()
                .brand(brand)
                .productName(request.getProductName())
                .productDesc(request.getProductDesc())
                .basePrice(request.getBasePrice())
                .productSizes(new ArrayList<>())
                .productWeights(new ArrayList<>())
                .createdAt(dateTimeConverter.formatDate(LocalDate.now()))
                .build();

//        Set list size for product
        request.getProductSizeDTOS().stream().forEach(productSizeDTO -> {
            Size size = sizeRepository.findById(productSizeDTO.getSizeId())
                    .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOTFOUND_EXCEPTION));
            ProductSize productSize = ProductSize.builder()
                    .keyProductSize(KeyProductSize.builder().sizeId(size.getId()).product(product).build())
                    .size(size)
                    .priceScale(productSizeDTO.getPriceScale())
                    .build();
            product.addProductSize(productSize);
        });

//        Set list weight for product
        request.getProductWeightDTOS().stream().forEach(productWeightDTO -> {
            Weight weight = weightRepository.findById(productWeightDTO.getWeightId())
                    .orElseThrow(() -> new AppException(ErrorCode.WEIGHT_NOTFOUND_EXCEPTION));
            ProductWeight productWeight = ProductWeight.builder()
                    .keyProductWeight(KeyProductWeight.builder().weightId(weight.getId()).product(product).build())
                    .weight(weight)
                    .priceScale(productWeightDTO.getPriceScale())
                    .build();
            product.addProductWeight(productWeight);
        });
        productRepository.save(product);
    }

    @Override
    public void modifyProduct() {

    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        if(productRepository.deleteAndGetCountById(id) == 0) {
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND_EXCEPTION);
        }
    }
}
