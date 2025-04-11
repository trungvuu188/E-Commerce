package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.dto.request.AuthenticationRequest;
import com.e_commerce.grocery_mart.dto.response.AuthenticationResponse;
import com.e_commerce.grocery_mart.entity.Customer;
import com.e_commerce.grocery_mart.entity.Role;
import com.e_commerce.grocery_mart.entity.User;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.repository.CustomerRepository;
import com.e_commerce.grocery_mart.repository.RoleRepository;
import com.e_commerce.grocery_mart.repository.UserRepository;
import com.e_commerce.grocery_mart.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    UserRepository userRepository;
    CustomerRepository customerRepository;
    RoleRepository roleRepository;

    @Override
    public AuthenticationResponse accountRegister(AuthenticationRequest request) {

        Role role = roleRepository.findByRoleName("CUSTOMER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOTFOUND_EXCEPTION));

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_EXISTED_EXCEPTION);
        }
        Customer customer = Customer.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(role)
                .build();
        customerRepository.save(customer);
        return null;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        User user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if(user == null) {
            throw new AppException(ErrorCode.LOGIN_FAILED_EXCEPTION);
        }
        return null;
    }
}
