package com.example.testproject.model.order;

import com.example.testproject.entity.OrderEntity;
import com.example.testproject.model.common.ApiResponse1;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends ApiResponse1 {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String orderNumber;
    private String orderName;
    private Long amount;
    private String status;

    public static Order toModel(OrderEntity order) {
        return Order.builder()
                .id(order.getId())
                .orderName(order.getOrderName())
                .orderNumber(order.getOrderNumber())
                .amount(order.getAmount())
                .status(order.getStatus())
                .build();
    }
}
