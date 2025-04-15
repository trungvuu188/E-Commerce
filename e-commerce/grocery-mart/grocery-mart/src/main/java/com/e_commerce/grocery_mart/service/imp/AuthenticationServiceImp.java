package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.constant.PredefinedRole;
import com.e_commerce.grocery_mart.dto.request.AccountRegisterRequest;
import com.e_commerce.grocery_mart.dto.request.IntrospectRequest;
import com.e_commerce.grocery_mart.dto.request.LoginRequest;
import com.e_commerce.grocery_mart.dto.request.LogoutRequest;
import com.e_commerce.grocery_mart.dto.response.AuthenticationResponse;
import com.e_commerce.grocery_mart.dto.response.CustomerAuthResponse;
import com.e_commerce.grocery_mart.dto.response.IntrospectResponse;
import com.e_commerce.grocery_mart.dto.response.AdminAuthResponse;
import com.e_commerce.grocery_mart.entity.Customer;
import com.e_commerce.grocery_mart.entity.InvalidatedToken;
import com.e_commerce.grocery_mart.entity.Role;
import com.e_commerce.grocery_mart.entity.User;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.repository.CustomerRepository;
import com.e_commerce.grocery_mart.repository.InvalidatedTokenRepository;
import com.e_commerce.grocery_mart.repository.RoleRepository;
import com.e_commerce.grocery_mart.repository.UserRepository;
import com.e_commerce.grocery_mart.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    UserRepository userRepository;
    CustomerRepository customerRepository;
    RoleRepository roleRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    long REFRESHABLE_DURATION;

    @Override
    public AuthenticationResponse accountRegister(AccountRegisterRequest request) {

        Role role = roleRepository.findByRoleName(PredefinedRole.CUSTOMER_ROLE)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOTFOUND_EXCEPTION));

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_EXISTED_EXCEPTION);
        }
        Customer customer = Customer.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)

                .build();
        customerRepository.save(customer);
        return login(LoginRequest.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .build());
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername());
        if(user == null) {
            throw new AppException(ErrorCode.USER_NOTFOUND_EXCEPTION);
        }

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED_EXCEPTION);

        var token = generateToken(user);

        if(user.getRole().getRoleName() == PredefinedRole.CUSTOMER_ROLE) {
            Customer customer = (Customer) user;
            CustomerAuthResponse response = CustomerAuthResponse.builder()
                    .token(token)
                    .fullName(customer.getFullName())
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .avatarUrl(customer.getAvatarUrl())
                    .phoneNumber(customer.getPhone())
                    .build();
            return response;
        } else if(user.getRole().getRoleName() == PredefinedRole.ADMIN_ROLE) {
            AdminAuthResponse response = AdminAuthResponse.builder()
                    .token(token)
                    .build();
            return response;
        } else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    @Override
    public void logout(LogoutRequest request) {
        try {
            var signToken = verifyToken(request.getToken());

            String jwtId = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jwtId)
                    .expiryTime(expiryTime)
                    .build();

            invalidatedTokenRepository.save(invalidatedToken);

        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void deleteAccount(UUID accountId) {
        if(userRepository.deleteAndCountById(accountId) == 0) {
            throw new AppException(ErrorCode.USER_NOTFOUND_EXCEPTION);
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder().isValid(isValid).build();
    }

    @Override
    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("404Xuv.dev")
                .issueTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if(!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED_EXCEPTION);

        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED_EXCEPTION);
        }

        return signedJWT;
    }

    @Override
    public String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(user.getRole() != null) {
            stringJoiner.add("ROLE_" + user.getRole().getRoleName());
        }
        return stringJoiner.toString();
    }
}
