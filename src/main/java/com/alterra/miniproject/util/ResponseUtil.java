package com.alterra.miniproject.util;

import java.time.LocalDateTime;

import com.alterra.miniproject.constant.constants;
import com.alterra.miniproject.domain.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    private ResponseUtil() {}

    public static <T> ResponseEntity<Object> build(constants.ResponseCode responseCode, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(responseCode, data), httpStatus);
    }

    private static <T> ApiResponse<T> build(constants.ResponseCode responseCode, T data) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .responseCode(responseCode.name())
                .message(responseCode.getMessage())
                .data(data)
                .build();
    }
}
