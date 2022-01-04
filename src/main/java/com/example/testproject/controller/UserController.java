package com.example.testproject.controller;

import com.example.testproject.entity.UserEntity;
import com.example.testproject.exception.SystemException;
import com.example.testproject.model.common.ApiResponse;
import com.example.testproject.model.common.ApiResponseError;
import com.example.testproject.model.user.User;
import com.example.testproject.model.user.UsersList;
import com.example.testproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> registerUsers(@RequestBody UserEntity user) {
        log.info("Called method create new user");
        List<ApiResponseError> errors = new ArrayList<>();
        if (user.getEmail() == null) {
            errors.add(ApiResponseError.badRequest("Field is required: email"));
            return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
        }
        if (user.getPassword() == null) {
            errors.add(ApiResponseError.badRequest("Field is required: password"));
            return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
        }
        if (userService.findUser(user.getEmail()) != null) {
            String message = String.format("User already exists, email: %s", user.getEmail());
            throw new NonUniqueResultException(message);
        }
        User newUser = User.toUserModel(userService.createUser(user));
        return ResponseEntity.ok(ApiResponse.okContent(newUser));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        log.debug("Called method find user by id {}", id);
        List<ApiResponseError> errors = new ArrayList<>();
        if (userService.findUser(id) == null) {
            errors.add(ApiResponseError.notFound(String.format("User with id %d not found", id)));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.apiErrors(errors));
        }
        if (Long.parseLong(id.toString()) == 0) {
            String message = String.format("Invalid input: %s", id);
            throw new NonUniqueResultException(message);
        }
        return ResponseEntity.ok(ApiResponse.okContent(User.toUserModel(userService.findUser(id))));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUsersList() {
        log.debug("Called method find all users");
        List<ApiResponseError> errors = new ArrayList<>();
        try {
            List<User> users = userService.findAllUsers().stream().map(User::toUserModel).collect(Collectors.toList());
            return ResponseEntity.ok(ApiResponse.okContent(UsersList.builder()
                    .users(users)
                    .count(users.size())
                    .build()));
        } catch (SystemException e) {
            errors.add(ApiResponseError.badRequest(e.getMessage()));
            return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.debug("Called method delete user with id {}", id);
        List<ApiResponseError> errors = new ArrayList<>();
        if (userService.findUser(id) == null) {
            errors.add(ApiResponseError.notFound(String.format("User with id %d not found", id)));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.apiErrors(errors));
        }
        userService.removeUser(id);
        return ResponseEntity.ok(String.format("User with id: %d was removed successfully", id));

    }
}
