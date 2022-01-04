package com.example.testproject.controller;

import com.example.testproject.entity.OrderEntity;
import com.example.testproject.exception.CustomBadRequestException;
import com.example.testproject.exception.CustomNotFoundException;
import com.example.testproject.model.common.ApiResponseError;
import com.example.testproject.model.order.Order;
import com.example.testproject.model.order.OrderApiResponse;
import com.example.testproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @PostMapping("/order")
//    public ResponseEntity createOrder(@RequestBody OrderEntity order, @RequestParam Long userId){
//        List<ApiResponseError> errors = new ArrayList<>();
//        try {
//            return ResponseEntity.ok(OrderApiResponse.buildApiResponseObject(orderService.create(order, userId)));
//        } catch (CustomBadRequestException e) {
//            errors.add(ApiResponseError.buildApiError(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage()));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseError.buildApiResponseError(errors));
//        } catch (CustomNotFoundException e) {
//            errors.add(ApiResponseError.buildApiError(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage()));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseError.buildApiResponseError(errors));
//        }
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity getUserOrders(@RequestParam Long userId) {
//        List<ApiResponseError> errors = new ArrayList<>();
//        try {
//            List<Order> orders = orderService.getAllUserOrders(userId);
//            return ResponseEntity.ok(OrderApiResponse.buildApiResponseList(orders));
//        } catch (CustomNotFoundException e) {
//            errors.add(ApiResponseError.buildApiError(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage()));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseError.buildApiResponseError(errors));
//        }
//    }
//
//    @GetMapping("/order/{id}")
//    public ResponseEntity getOrderInfo(@PathVariable Long id) {
//        List<ApiResponseError> errors = new ArrayList<>();
//        try {
//            return ResponseEntity.ok(OrderApiResponse.buildApiResponseObject(orderService.getOrder(id)));
//        } catch (CustomNotFoundException e) {
//            errors.add(ApiResponseError.buildApiError(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage()));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseError.buildApiResponseError(errors));
//        }
//    }
//
//    @PatchMapping("/order/{id}")
//    public ResponseEntity editOrder(@RequestBody OrderEntity order, @PathVariable Long id) {
//        List<ApiResponseError> errors = new ArrayList<>();
//        try {
//            return ResponseEntity.ok(OrderApiResponse.buildApiResponseObject(orderService.updateOrder(order, id)));
//        } catch (CustomBadRequestException e) {
//            errors.add(ApiResponseError.buildApiError(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage()));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseError.buildApiResponseError(errors));
//        } catch (CustomNotFoundException e) {
//            errors.add(ApiResponseError.buildApiError(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage()));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseError.buildApiResponseError(errors));
//        }
//    }

}
