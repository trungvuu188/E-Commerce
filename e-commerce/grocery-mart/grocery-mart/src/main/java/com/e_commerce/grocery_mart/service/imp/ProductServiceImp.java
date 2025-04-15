package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.ProductCreationRequest;
import com.e_commerce.grocery_mart.dto.request.ProductModifyRequest;
import com.e_commerce.grocery_mart.dto.response.ProductDTO;
import com.e_commerce.grocery_mart.dto.response.ProductSizeDTO;
import com.e_commerce.grocery_mart.dto.response.ProductWeightDTO;
import com.e_commerce.grocery_mart.entity.*;
import com.e_commerce.grocery_mart.entity.keys.KeyProductSize;
import com.e_commerce.grocery_mart.entity.keys.KeyProductWeight;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.helper.DateTimeConverter;
import com.e_commerce.grocery_mart.mapper.ProductMapper;
import com.e_commerce.grocery_mart.repository.*;
import com.e_commerce.grocery_mart.service.BrandService;
import com.e_commerce.grocery_mart.service.ProductService;
import com.e_commerce.grocery_mart.service.ProductSpecification;
import com.e_commerce.grocery_mart.service.ProductSubService;
import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    BrandService brandService;
    ProductSubService productSubService;
    ProductMapper productMapper;
    DateTimeConverter dateTimeConverter;
    ProductRepository productRepository;
    ProductSizeRepository productSizeRepository;
    ProductWeightRepository productWeightRepository;
    UserRepository userRepository;
    ProductSpecification productSpecification;

    @Override
    public List<ProductDTO> getAllProduct(Integer brandId, String brandName, Integer sizeId, Integer weightId) {

        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products;
        Specification<Product> filters = Specification
                .where(brandId == null ? null : productSpecification.brandLike(brandId))
                .and(StringUtils.isEmpty(brandName) ? null : productSpecification.brandNameLike(brandName))
                .and(sizeId == null ? null : productSpecification.sizeLike(sizeId))
                .and(weightId == null ? null : productSpecification.weightLike(weightId));
        products = productRepository.findAll(filters);

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
    public Product getBaseProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND_EXCEPTION));
    }

    @Override
    @Transactional
    public void addProduct(ProductCreationRequest request) {

        if(productRepository.existsByProductName(request.getProductName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED_EXCEPTION);
        }
        Brand brand = brandService.getBrandById(request.getBrandId());

        Product product = Product.builder()
                .brand(brand)
                .productName(request.getProductName())
                .productDesc(request.getProductDesc())
                .basePrice(request.getBasePrice())
                .productSizes(new ArrayList<>())
                .productWeights(new ArrayList<>())
                .createdAt(dateTimeConverter.formatDate(LocalDate.now()))
                .build();
        updateProductSize(product, request.getProductSizeDTOS());
        updateProductWeight(product, request.getProductWeightDTOS());

        productRepository.save(product);
    }

    @Override
    public void modifyProduct(int productId, ProductModifyRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND_EXCEPTION));

        boolean isModified = false;

        if(request.getProductName() != null && !request.getProductName().isBlank()) {
            product.setProductName(request.getProductName());
            isModified = true;
        }

        if(request.getProductDesc() != null && !request.getProductDesc().isBlank()) {
            product.setProductDesc(request.getProductDesc());
            isModified = true;
        }

        if(request.getBasePrice() != 0) {
            product.setBasePrice(request.getBasePrice());
            isModified = true;
        }

        if(request.getImageUrl() != null && request.getImageUrl().isBlank()) {
            product.setImageURL(request.getImageUrl());
            isModified = true;
        }

        if(request.getProductSizeDTOS() != null && !request.getProductSizeDTOS().isEmpty()) {
            updateProductSize(product, request.getProductSizeDTOS());
            isModified = true;
        }

        if(request.getProductWeightDTOS() != null && !request.getProductWeightDTOS().isEmpty()) {
            updateProductWeight(product, request.getProductWeightDTOS());
            isModified = true;
        }

        if(!isModified) {
            throw new AppException(ErrorCode.PRODUCT_UNCHANGED_EXCEPTION);
        }

        if(isModified) {
//            User user = userRepository.findById(request.getModifyPersonId())
//                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND_EXCEPTION));
//            product.setModifyPerson(user);
            product.setModifiedAt(dateTimeConverter.formatDate(LocalDate.now()));
            productRepository.save(product);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        if(productRepository.deleteAndGetCountById(id) == 0) {
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND_EXCEPTION);
        }
    }

    @Override
    public void updateProductSize(Product product, List<ProductSizeDTO> productSizeDTOS) {
        product.getProductSizes().clear();
        productSizeDTOS.stream().forEach(productSizeDTO -> {
            Size size = productSubService.getSizeById(productSizeDTO.getSizeId());
            ProductSize productSize = ProductSize.builder()
                    .keyProductSize(KeyProductSize.builder().sizeId(size.getId()).product(product).build())
                    .size(size)
                    .priceScale(productSizeDTO.getPriceScale())
                    .build();
            product.addProductSize(productSize);
        });
    }

    @Override
    public void updateProductWeight(Product product, List<ProductWeightDTO> productWeightDTOS) {
        product.getProductWeights().clear();
        productWeightDTOS.stream().forEach(productWeightDTO -> {
            Weight weight = productSubService.getWeightById(productWeightDTO.getWeightId());
            ProductWeight productWeight = ProductWeight.builder()
                    .keyProductWeight(KeyProductWeight.builder().weightId(weight.getId()).product(product).build())
                    .weight(weight)
                    .priceScale(productWeightDTO.getPriceScale())
                    .build();
            product.addProductWeight(productWeight);
        });
    }

    @Override
    public double calculateProductPrice(int productId, int sizeId, int weightId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND_EXCEPTION));
        KeyProductSize keyProductSize = KeyProductSize.builder()
                .sizeId(sizeId)
                .product(product)
                .build();
        ProductSize productSize = productSizeRepository.findByKeyProductSize(keyProductSize);
        KeyProductWeight keyProductWeight = KeyProductWeight.builder()
                .weightId(weightId)
                .product(product)
                .build();
        ProductWeight productWeight = productWeightRepository.findByKeyProductWeight(keyProductWeight);
        double sizeScalePrice = product.getBasePrice() * productSize.getPriceScale();
        double weightScalePrice = product.getBasePrice() * productWeight.getPriceScale();
        double totalPrice = (product.getBasePrice() + sizeScalePrice + weightScalePrice) * quantity;
        return totalPrice;
    }
}
