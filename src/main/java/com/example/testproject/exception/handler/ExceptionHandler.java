package com.example.testproject.exception.handler;

import com.example.testproject.model.common.ApiResponse;
import com.example.testproject.model.common.ApiResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        List<ApiResponseError> errors = new ArrayList<>();
        errors.add(ApiResponseError.badRequest(e.getMessage()));
        log.info("Exception message {}", e.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormatException(NumberFormatException e) {
        List<ApiResponseError> errors = new ArrayList<>();
        errors.add(ApiResponseError.badRequest(e.getMessage()));
        log.info("Exception message {}", e.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NonUniqueResultException.class)
    public ResponseEntity<?> handleNonUniqueResultException(NonUniqueResultException e) {
        List<ApiResponseError> errors = new ArrayList<>();
        errors.add(ApiResponseError.badRequest(e.getMessage()));
        log.info("Exception message {}", e.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
    }
}
