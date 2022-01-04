package com.example.testproject.model.order;

import com.example.testproject.model.common.ApiResponse1;
import com.example.testproject.model.order.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersList extends ApiResponse1 {
    private List<Order> orders;
    private Integer count;
}
