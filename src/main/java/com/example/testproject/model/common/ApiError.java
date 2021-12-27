package com.example.testproject.model.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiError {
    private String code;
    private String message;

    public static ApiError buildApiError(String code, String message) {
        return ApiError.builder()
                .code(code)
                .message(message)
                .build();
    }
}
