package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.BrandCreationRequest;
import com.e_commerce.grocery_mart.dto.response.BrandDTO;

import java.util.List;

public interface BrandService {
    List<BrandDTO> getAllBrands();
    BrandDTO getBrandById(int id);
    void addBrand(BrandCreationRequest request);
    void removeBrand(int brandId);
}
