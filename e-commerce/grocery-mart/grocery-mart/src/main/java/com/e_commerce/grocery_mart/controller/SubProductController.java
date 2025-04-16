package com.e_commerce.grocery_mart.controller;

import com.e_commerce.grocery_mart.dto.request.SizeCreationRequest;
import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import com.e_commerce.grocery_mart.dto.response.SizeDTO;
import com.e_commerce.grocery_mart.service.ProductSubService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sub")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubProductController {

    ProductSubService productSubService;

    @GetMapping("/size")
    ApiResponse<List<SizeDTO>> getAllSizes(){
        return ApiResponse.<List<SizeDTO>>builder()
                .result(productSubService.getSizes())
                .build();
    }

    @PostMapping("/size")
    ApiResponse<Void> createSize(@RequestBody SizeCreationRequest request){
        productSubService.addSize(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/size/{id}")
    ApiResponse<Void> deleteSize(@PathVariable int id){
        productSubService.removeSize(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
