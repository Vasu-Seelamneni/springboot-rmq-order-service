package com.mqs.orderservice.dto;

import com.mqs.orderservice.constants.OrderStatus;

import java.io.Serializable;

public class OrderEvent implements Serializable {
    private OrderStatus status;
    private String message;
    private Order order;

    public OrderEvent() { }

    public OrderEvent(OrderStatus status, String message, Order order) {
        this.status = status;
        this.message = message;
        this.order = order;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", order=" + order +
                '}';
    }
}

