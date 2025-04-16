package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.SizeCreationRequest;
import com.e_commerce.grocery_mart.dto.response.SizeDTO;
import com.e_commerce.grocery_mart.entity.Size;

import java.util.List;

public interface ProductSubService {

    List<SizeDTO> getSizes();
    Size getSizeById(int sizeId);
    void addSize(SizeCreationRequest request);
    void removeSize(int sizeId);
}
