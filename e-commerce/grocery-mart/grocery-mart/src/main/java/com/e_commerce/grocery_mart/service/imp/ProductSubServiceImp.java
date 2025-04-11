package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.SizeCreationRequest;
import com.e_commerce.grocery_mart.dto.request.WeightCreationRequest;
import com.e_commerce.grocery_mart.dto.response.SizeDTO;
import com.e_commerce.grocery_mart.dto.response.WeightDTO;
import com.e_commerce.grocery_mart.entity.Size;
import com.e_commerce.grocery_mart.entity.Weight;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.repository.SizeRepository;
import com.e_commerce.grocery_mart.repository.WeightRepository;
import com.e_commerce.grocery_mart.service.ProductSubService;
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
public class ProductSubServiceImp implements ProductSubService {

    SizeRepository sizeRepository;
    WeightRepository weightRepository;

    @Override
    public List<SizeDTO> getSizes() {

        List<SizeDTO> sizeDTOS = new ArrayList<>();
        List<Size> sizes = sizeRepository.findAll();
        for(Size size : sizes) {
            SizeDTO sizeDTO = SizeDTO.builder()
                    .id(size.getId())
                    .sizeName(size.getSizeName())
                    .build();
            sizeDTOS.add(sizeDTO);
        }
        return sizeDTOS;
    }

    @Override
    public void addSize(SizeCreationRequest request) {
        if(sizeRepository.existsBySizeName(request.getSizeName().toUpperCase())) {
            throw new AppException(ErrorCode.SIZE_EXISTED_EXCEPTION);
        }
        Size size = Size.builder()
                .sizeName(request.getSizeName().toUpperCase())
                .build();
        sizeRepository.save(size);
    }

    @Override
    @Transactional
    public void removeSize(int sizeId) {
        if(sizeRepository.deleteAndGetCountById(sizeId) == 0) {
            throw new AppException(ErrorCode.SIZE_NOTFOUND_EXCEPTION);
        }
    }

    @Override
    public List<WeightDTO> getWeights() {

        List<WeightDTO> weightDTOS = new ArrayList<>();
        List<Weight> weights = weightRepository.findAll();
        for(Weight weight : weights) {
            WeightDTO weightDTO = WeightDTO.builder()
                    .id(weight.getId())
                    .weightName(weight.getWeightName())
                    .build();
            weightDTOS.add(weightDTO);
        }
        return weightDTOS;
    }

    @Override
    public void addWeight(WeightCreationRequest request) {
        if(weightRepository.existsByWeightName(request.getWeightName().toUpperCase())) {
            throw new AppException(ErrorCode.WEIGHT_EXISTED_EXCEPTION);
        }
        Weight weight = Weight.builder()
                .weightName(request.getWeightName())
                .build();
        weightRepository.save(weight);
    }

    @Override
    @Transactional
    public void removeWeight(int weightId) {
        if(weightRepository.deleteAndGetCountById(weightId) == 0) {
            throw new AppException(ErrorCode.WEIGHT_NOTFOUND_EXCEPTION);
        }
    }
}
