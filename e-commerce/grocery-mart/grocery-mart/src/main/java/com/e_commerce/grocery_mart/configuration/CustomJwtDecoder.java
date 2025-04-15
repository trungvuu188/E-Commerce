package com.e_commerce.grocery_mart.configuration;

import com.e_commerce.grocery_mart.dto.request.IntrospectRequest;
import com.e_commerce.grocery_mart.dto.response.IntrospectResponse;
import com.e_commerce.grocery_mart.service.AuthenticationService;
import com.e_commerce.grocery_mart.service.imp.AuthenticationServiceImp;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey}")
    String signerKey;

    final AuthenticationService authenticationService;

    NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) {

        try {
            var response = authenticationService.introspect(IntrospectRequest.builder().token(token).build());
            if(!response.isValid()) throw new JWKException("Token invalid");
        } catch (ParseException | JOSEException e) {
            throw new JwtException(e.getMessage());
        }

        if(Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
