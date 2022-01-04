package com.example.testproject.model.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponseError {
    private String code;
    private String message;
    private String data;

    public static ApiResponseError notFound(String message) {
        return ApiResponseError.builder()
                .code("NOT_FOUND")
                .message(message)
                .build();
    }

    public static ApiResponseError badRequest(String message) {
        return ApiResponseError.builder()
                .code("BAD_REQUEST")
                .message(message)
                .build();
    }
}
