package com.e_commerce.grocery_mart.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    ROLE_NOTFOUND_EXCEPTION(5000, "Role is not found"),
    USERNAME_EXISTED_EXCEPTION(5000, "Username is already existed"),
    LOGIN_FAILED_EXCEPTION(5000, "Incorrect username or password")
    ;

    int code;
    String message;
}
