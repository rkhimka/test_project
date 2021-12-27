package com.example.testproject.model.order;

import com.example.testproject.model.common.ApiResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersList extends ApiResponse {
    private List<Order> orders;
    private Integer count;
}
