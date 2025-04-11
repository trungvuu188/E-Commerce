package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.BrandCreationRequest;
import com.e_commerce.grocery_mart.dto.response.BrandDTO;
import com.e_commerce.grocery_mart.entity.Brand;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.repository.BrandRepository;
import com.e_commerce.grocery_mart.service.BrandService;
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
public class BrandServiceImp implements BrandService {

    BrandRepository brandRepository;

    @Override
    public List<BrandDTO> getAllBrands() {

        List<BrandDTO> brandDTOS = new ArrayList<>();
        List<Brand> brands = brandRepository.findAll();
        for(Brand brand : brands) {
            BrandDTO brandDTO = BrandDTO.builder()
                    .id(brand.getId())
                    .brandName(brand.getBrandName())
                    .build();
            brandDTOS.add(brandDTO);
        }
        return brandDTOS;
    }

    @Override
    public BrandDTO getBrandById(int id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOTFOUND_EXCEPTION));
        return BrandDTO.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .build();
    }

    @Override
    public void addBrand(BrandCreationRequest request) {
        if(brandRepository.existsByBrandName(request.getBrandName())) {
            throw new AppException(ErrorCode.BRAND_EXISTED_EXCEPTION);
        }
        Brand brand = Brand.builder()
                .brandName(request.getBrandName())
                .build();
        brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void removeBrand(int brandId) {
        if(brandRepository.deleteAndGetCountById(brandId) == 0) {
            throw new AppException(ErrorCode.BRAND_NOTFOUND_EXCEPTION);
        }
    }
}
