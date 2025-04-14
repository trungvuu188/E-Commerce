package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.AuthenticationRequest;
import com.e_commerce.grocery_mart.dto.response.AuthenticationResponse;

import java.util.UUID;

public interface AuthenticationService {

    AuthenticationResponse accountRegister(AuthenticationRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
    void deleteAccount(UUID accountId);
}
