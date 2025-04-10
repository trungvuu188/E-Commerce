package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.AuthenticationRequest;
import com.e_commerce.grocery_mart.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse accountRegister(AuthenticationRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
