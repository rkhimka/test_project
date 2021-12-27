package com.example.testproject.controller;

import com.example.testproject.entity.UserEntity;
import com.example.testproject.exception.CustomBadRequestException;
import com.example.testproject.exception.CustomNotFoundException;
import com.example.testproject.model.common.ApiError;
import com.example.testproject.model.common.ApiResponseError;
import com.example.testproject.model.user.User;
import com.example.testproject.model.user.UserApiResponse;
import com.example.testproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity registerUsers(@RequestBody UserEntity user) {
        List<ApiError> errors = new ArrayList<>();
        try {
            userService.createUser(user);
            return ResponseEntity.ok(UserApiResponse.buildApiResponseObject(User.toModel(user)));
        } catch (CustomBadRequestException e) {
            errors.add(ApiError.buildApiError(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseError.buildApiResponseError(errors));
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUserInfo(@PathVariable Long id) {
        List<ApiError> errors = new ArrayList<>();
        try {
            UserEntity user = userService.getUser(id);
            return ResponseEntity.ok(User.toModel(user));
        } catch (CustomNotFoundException e) {
            errors.add(ApiError.buildApiError(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseError.buildApiResponseError(errors));
        }
    }

    @GetMapping("/list")
    public ResponseEntity getAllUsers() {
        List<ApiError> errors = new ArrayList<>();
        try {
            List<User> users = userService.getUsers();
            return ResponseEntity.ok(UserApiResponse.buildApiResponseList(users));
        } catch (Exception e) {
            errors.add(ApiError.buildApiError(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    userService.getMessageSource().getMessage("system.error", new Object[0],
                            new Locale("en"))));
            return ResponseEntity.badRequest().body(ApiResponseError.buildApiResponseError(errors));
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        List<ApiError> errors = new ArrayList<>();
        try {
            userService.removeUser(id);
            return ResponseEntity.ok(userService.getMessageSource().getMessage("operation.success", new Object[0],
                    new Locale("en")));
        } catch (CustomNotFoundException e) {
            errors.add(ApiError.buildApiError(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseError.buildApiResponseError(errors));
        }
    }
}
