package com.example.testproject.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Payload;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Payload {
    private T data;
    private List<ApiResponseError> errors;

    public static ApiResponse<?> apiErrors(List<ApiResponseError> errorsList) {
        return ApiResponse.builder()
                .errors(errorsList)
                .build();
    }

    public static ApiResponse<?> okContent(Payload data) {
        return ApiResponse.builder()
                .data(data)
                .build();
    }

}
