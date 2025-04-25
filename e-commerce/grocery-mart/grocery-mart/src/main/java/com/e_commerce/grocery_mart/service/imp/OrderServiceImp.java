package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    @Override
    public void createOrder() {

    }

    @Override
    public void deleteOrder() {

    }
}
