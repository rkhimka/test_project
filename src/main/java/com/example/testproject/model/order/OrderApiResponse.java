package com.example.testproject.model.order;

import com.example.testproject.model.common.ApiResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderApiResponse extends ApiResponse {
    private ApiResponse data;

    public static ApiResponse buildApiResponseList(List<Order> orders) {
        return OrderApiResponse.builder()
                .data(OrdersList.builder()
                        .orders(orders)
                        .count(orders.size())
                        .build())
                .build();
    }

    public static ApiResponse buildApiResponseObject(Order order) {
        return OrderApiResponse.builder()
                .data(order)
                .build();
    }
}
