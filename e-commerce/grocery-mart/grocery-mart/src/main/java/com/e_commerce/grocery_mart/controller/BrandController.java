package com.e_commerce.grocery_mart.controller;

import com.e_commerce.grocery_mart.dto.request.BrandCreationRequest;
import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import com.e_commerce.grocery_mart.dto.response.BrandDTO;
import com.e_commerce.grocery_mart.service.BrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/brand")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BrandController {

    BrandService brandService;

    @GetMapping
    ApiResponse<List<BrandDTO>> getAllBrands() {
        return ApiResponse.<List<BrandDTO>>builder()
                .result(brandService.getAllBrands())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<BrandDTO> getBrandById(@PathVariable int id) {
        return ApiResponse.<BrandDTO>builder()
                .result(brandService.getBrandById(id))
                .build();
    }

    @PostMapping
    ApiResponse<Void> createBrand(@RequestBody BrandCreationRequest request) {
        brandService.addBrand(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteBrand(@PathVariable int id) {
        brandService.removeBrand(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
