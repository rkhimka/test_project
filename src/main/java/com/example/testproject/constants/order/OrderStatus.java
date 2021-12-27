package com.example.testproject.constants.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    REJECTED("REJECTED"),
    CLOSED("CLOSED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
