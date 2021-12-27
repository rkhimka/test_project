package com.example.testproject.model.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiResponseError extends ApiResponse {
    private List<ApiError> errors;

    public static ApiResponse buildApiResponseError(List<ApiError> errors) {
        return ApiResponseError.builder()
                .errors(errors)
                .build();
    }
}
