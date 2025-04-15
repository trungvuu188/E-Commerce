package com.e_commerce.grocery_mart.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class CustomerAuthResponse extends AuthenticationResponse {

    String avatarUrl;
    String fullName;
    String email;
    String phoneNumber;
    String password;
}
