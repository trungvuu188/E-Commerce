package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.SizeCreationRequest;
import com.e_commerce.grocery_mart.dto.request.WeightCreationRequest;
import com.e_commerce.grocery_mart.dto.response.SizeDTO;
import com.e_commerce.grocery_mart.dto.response.WeightDTO;

import java.util.List;

public interface ProductSubService {

    List<SizeDTO> getSizes();
    void addSize(SizeCreationRequest request);
    void removeSize(int sizeId);
    List<WeightDTO> getWeights();
    void addWeight(WeightCreationRequest request);
    void removeWeight(int weightId);
}
