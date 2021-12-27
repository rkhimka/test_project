package com.example.testproject.repository;

import com.example.testproject.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    OrderEntity findByOrderNumber(String orderNumber);

}
