package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.SizeCreationRequest;
import com.e_commerce.grocery_mart.dto.request.WeightCreationRequest;
import com.e_commerce.grocery_mart.dto.response.SizeDTO;
import com.e_commerce.grocery_mart.dto.response.WeightDTO;
import com.e_commerce.grocery_mart.entity.Size;
import com.e_commerce.grocery_mart.entity.Weight;

import java.util.List;

public interface ProductSubService {

    List<SizeDTO> getSizes();
    Size getSizeById(int sizeId);
    void addSize(SizeCreationRequest request);
    void removeSize(int sizeId);
    List<WeightDTO> getWeights();
    Weight getWeightById(int weightId);
    void addWeight(WeightCreationRequest request);
    void removeWeight(int weightId);
}
