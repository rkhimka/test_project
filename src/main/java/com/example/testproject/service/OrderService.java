package com.example.testproject.service;

import com.example.testproject.constants.order.OrderStatus;
import com.example.testproject.entity.OrderEntity;
import com.example.testproject.exception.CustomBadRequestException;
import com.example.testproject.exception.CustomNotFoundException;
import com.example.testproject.model.order.Order;
import com.example.testproject.repository.OrderRepository;
import com.example.testproject.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Getter
    private final MessageSource messageSource;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, MessageSource messageSource) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    public Order create(OrderEntity order, Long id) throws CustomBadRequestException, CustomNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new CustomNotFoundException(messageSource.getMessage("user.not.found",
                    new Object[0], new Locale("en")));
        } else if (orderRepository.findByOrderNumber(order.getOrderNumber()) != null) {
            throw new CustomBadRequestException(messageSource.getMessage("order.already.exists",
                    new Object[0], new Locale("en")));
        }
        order.setStatus("NEW");
        order.setUser(userRepository.findById(id).get());
        return Order.toModel(orderRepository.save(order));
    }

    public Order getOrder(Long id) throws CustomNotFoundException {
        if (orderRepository.findById(id).isEmpty()) {
            throw new CustomNotFoundException(messageSource.getMessage("order.not.found",
                    new Object[0], new Locale("en")));
        }
        return Order.toModel(orderRepository.findById(id).get());
    }

    public List<Order> getAllUserOrders(Long userId) throws CustomNotFoundException {
        if (userRepository.findById(userId).isEmpty()) {
            throw new CustomNotFoundException(messageSource.getMessage("user.not.found",
                    new Object[0], new Locale("en")));
        }
        List<OrderEntity> orders = userRepository.findById(userId).get().getOrders();
        return orders.stream().map(Order::toModel).collect(Collectors.toList());
    }

//    TODO: implement partial updating here
    public Order updateOrder(OrderEntity newOrderData, Long orderId) throws CustomBadRequestException,
            CustomNotFoundException {
        if (orderRepository.findById(orderId).isEmpty()) {
            throw new CustomNotFoundException(messageSource.getMessage("order.not.found",
                    new Object[0], new Locale("en")));
        }
        OrderEntity order = orderRepository.findById(orderId).get();
        order.setOrderName(newOrderData.getOrderName());
        order.setOrderNumber(newOrderData.getOrderNumber());
        order.setAmount(newOrderData.getAmount());
        if (!ObjectUtils.containsConstant(OrderStatus.values(), newOrderData.getStatus(), false)) {
            throw new CustomBadRequestException(messageSource.getMessage("order.wrong.status",
                    new Object[0], new Locale("en")));
        }
        order.setStatus(OrderStatus.valueOf(newOrderData.getStatus().toUpperCase()).getValue());
        return Order.toModel(orderRepository.save(order));
    }
}
