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
// Global Exception
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
// User Exception
    ROLE_NOTFOUND_EXCEPTION(5000, "Role is not found"),
    USERNAME_EXISTED_EXCEPTION(5000, "Username is already existed"),
    LOGIN_FAILED_EXCEPTION(5000, "Incorrect username or password"),
//Date Exception
    INVALID_DATE_EXCEPTION(5000, "Invalid date format"),
//Product
    SIZE_EXISTED_EXCEPTION(5000, "Size is already existed"),
    SIZE_NOTFOUND_EXCEPTION(5000, "Size is not found"),
    WEIGHT_EXISTED_EXCEPTION(5000, "Weight is already existed"),
    WEIGHT_NOTFOUND_EXCEPTION(5000, "Weight is not found"),
    BRAND_EXISTED_EXCEPTION(5000, "Brand is already existed"),
    BRAND_NOTFOUND_EXCEPTION(5000, "Brand is not found"),
    PRODUCT_EXISTED_EXCEPTION(5000, "Product is already existed"),
    PRODUCT_NOTFOUND_EXCEPTION(5000, "Product is not found"),
    ;

    int code;
    String message;
}
