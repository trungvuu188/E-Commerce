package com.e_commerce.grocery_mart.exception;

import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    Handle My App Exception
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException appException) {
        ErrorCode errorCode = appException.getErrorCode();
        return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(errorCode.getCode())
                            .message(errorCode.getMessage())
                            .build()
                    );
    }

//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handlingOtherException(RuntimeException runtimeException) {
//        return ResponseEntity.badRequest()
//                .body(ApiResponse.builder()
//                        .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
//                        .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
//                        .build()
//                );
//    }
}
