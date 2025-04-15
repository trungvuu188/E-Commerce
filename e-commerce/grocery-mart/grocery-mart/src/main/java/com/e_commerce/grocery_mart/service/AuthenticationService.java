package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.AccountRegisterRequest;
import com.e_commerce.grocery_mart.dto.request.IntrospectRequest;
import com.e_commerce.grocery_mart.dto.request.LoginRequest;
import com.e_commerce.grocery_mart.dto.request.LogoutRequest;
import com.e_commerce.grocery_mart.dto.response.AuthenticationResponse;
import com.e_commerce.grocery_mart.dto.response.IntrospectResponse;
import com.e_commerce.grocery_mart.dto.response.AdminAuthResponse;
import com.e_commerce.grocery_mart.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.UUID;

public interface AuthenticationService {

    AuthenticationResponse accountRegister(AccountRegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
    void logout(LogoutRequest request);
    void deleteAccount(UUID accountId);
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;
    String generateToken(User user);
    SignedJWT verifyToken(String token) throws JOSEException, ParseException;
    String buildScope(User user);
}
