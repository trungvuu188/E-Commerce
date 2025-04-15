package com.e_commerce.grocery_mart.controller;

import com.e_commerce.grocery_mart.dto.request.AccountRegisterRequest;
import com.e_commerce.grocery_mart.dto.request.LoginRequest;
import com.e_commerce.grocery_mart.dto.request.LogoutRequest;
import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import com.e_commerce.grocery_mart.dto.response.AuthenticationResponse;
import com.e_commerce.grocery_mart.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("/auth")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/register")
    ApiResponse<AuthenticationResponse> accountRegister(@RequestBody AccountRegisterRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.accountRegister(request))
                .build();
    }

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteAccount(@PathVariable UUID id) {
        authenticationService.deleteAccount(id);
        return ApiResponse.<Void>builder().build();
    }
}
