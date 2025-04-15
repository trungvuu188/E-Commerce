package com.e_commerce.grocery_mart.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
// Global Exception
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
// Authenticate
    UNAUTHENTICATED_EXCEPTION(9999, "Unauthenticated exception", HttpStatus.FORBIDDEN),

// User Exception
    ROLE_NOTFOUND_EXCEPTION(5000, "Role is not found", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED_EXCEPTION(5000, "Role is already existed", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED_EXCEPTION(5000, "Username is already existed", HttpStatus.BAD_REQUEST),
    USER_NOTFOUND_EXCEPTION(5000, "User is not existed", HttpStatus.BAD_REQUEST),
    LOGIN_FAILED_EXCEPTION(5000, "Incorrect username or password", HttpStatus.BAD_REQUEST),
//Date Exception
    INVALID_DATE_EXCEPTION(5000, "Invalid date format", HttpStatus.BAD_REQUEST),
//Product
    SIZE_EXISTED_EXCEPTION(5000, "Size is already existed", HttpStatus.BAD_REQUEST),
    SIZE_NOTFOUND_EXCEPTION(5000, "Size is not found", HttpStatus.BAD_REQUEST),
    WEIGHT_EXISTED_EXCEPTION(5000, "Weight is already existed", HttpStatus.BAD_REQUEST),
    WEIGHT_NOTFOUND_EXCEPTION(5000, "Weight is not found", HttpStatus.BAD_REQUEST),
    BRAND_EXISTED_EXCEPTION(5000, "Brand is already existed", HttpStatus.BAD_REQUEST),
    BRAND_NOTFOUND_EXCEPTION(5000, "Brand is not found", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED_EXCEPTION(5000, "Product is already existed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOTFOUND_EXCEPTION(5000, "Product is not found", HttpStatus.BAD_REQUEST),
    PRODUCT_UNCHANGED_EXCEPTION(5000, "Product is unchanged", HttpStatus.BAD_REQUEST),

//Warehouse
    WAREHOUSE_EXISTED_EXCEPTION(5000, "Warehouse name is already existed", HttpStatus.BAD_REQUEST),
    WAREHOUSE_NOTFOUND_EXCEPTION(5000, "Warehouse is not found", HttpStatus.BAD_REQUEST),
    PRODUCT_INVENTORY_NOTFOUND_EXCEPTION(5000, "Product Inventory is not found", HttpStatus.BAD_REQUEST),
    PRODUCT_INVENTORY_EXISTED_EXCEPTION(5000, "Product Inventory is already existed", HttpStatus.BAD_REQUEST),

//Cart
    CART_NOTFOUND_EXCEPTION(5000, "Cart is not found", HttpStatus.BAD_REQUEST),
    CART_ITEM_NOTFOUND_EXCEPTION(5000, "Cart item is not found", HttpStatus.BAD_REQUEST),

//CUSTOMER
    CUSTOMER_NOTFOUND_EXCEPTION(5000, "Customer is not found", HttpStatus.BAD_REQUEST),

    ;
    int code;
    String message;
    HttpStatusCode statusCode;
}
