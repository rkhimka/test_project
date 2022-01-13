package com.example.testproject.controller;

import com.example.testproject.entity.UserEntity;
import com.example.testproject.exception.SystemException;
import com.example.testproject.model.common.ApiResponse;
import com.example.testproject.model.common.ApiResponseError;
import com.example.testproject.model.user.User;
import com.example.testproject.model.user.UsersList;
import com.example.testproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.example.testproject.constants.common.CommonConstants.MAX_ALLOWED_COUNT;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private MessageSource messageSource;

    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUsers(@RequestBody UserEntity user) {
        log.info("Called method create new user");
        List<ApiResponseError> errors = new ArrayList<>();
        if (user.getEmail() == null) {
            String msg = messageSource.getMessage("field.required", new String[]{"email"}, Locale.ROOT);
            errors.add(ApiResponseError.badRequest(msg));
            return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
        }
        if (user.getPassword() == null) {
            String msg = messageSource.getMessage("field.required", new String[]{"password"}, Locale.ROOT);
            errors.add(ApiResponseError.badRequest(msg));
            return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
        }
        if (userService.findUser(user.getEmail()) != null) {
            String msg = messageSource.getMessage("user.email.exists", new String[]{user.getEmail()}, Locale.ROOT);
            throw new NonUniqueResultException(msg);
        }
        User newUser = User.toUserModel(userService.createUser(user));
        return ResponseEntity.ok(ApiResponse.okContent(newUser));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        log.debug("Called method find user by id {}", id);
        List<ApiResponseError> errors = new ArrayList<>();
        if (userService.findUser(id) == null) {
            String msg = messageSource.getMessage("user.not.found", new Object[]{id}, Locale.ROOT);
            errors.add(ApiResponseError.notFound(msg));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.apiErrors(errors));
        }
        if (Long.parseLong(id.toString()) == 0) {
            throw new MethodArgumentTypeMismatchException(null, null, null, null, null);
        }
        return ResponseEntity.ok(ApiResponse.okContent(User.toUserModel(userService.findUser(id))));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUsersList(
            @RequestParam(required = false, defaultValue = "1", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "count") Integer count,
            @PageableDefault
                    Pageable pageable) {
        log.debug("Called method find all users");
        List<ApiResponseError> errors = new ArrayList<>();
        if (pageable.getPageSize() > MAX_ALLOWED_COUNT) {
            String msg = messageSource.getMessage("count.max.value.invalid", null, Locale.ROOT);
            errors.add(ApiResponseError.badRequest(msg));
            return ResponseEntity.badRequest().body(ApiResponse.apiErrors(errors));
        }
        try {
            Page<UserEntity> users = userService.findAllUsers(pageable);
            return ResponseEntity.ok(ApiResponse.okContent(UsersList.builder()
                    .users(users.getContent().stream().map(User::toUserModel).collect(Collectors.toList()))
                    .page(users.getNumber() + 1)
                    .pages(users.getTotalPages())
                    .pageCount(users.getNumberOfElements())
                    .totalCount(users.getTotalElements())
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
            String msg = messageSource.getMessage("user.not.found", new Object[]{id}, Locale.ROOT);
            errors.add(ApiResponseError.notFound(msg));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.apiErrors(errors));
        }
        userService.removeUser(id);
        String msg = messageSource.getMessage("user.removed.successfully", new Object[]{id}, Locale.ROOT);
        return ResponseEntity.ok(msg);

    }
}
