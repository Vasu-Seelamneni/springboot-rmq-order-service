package com.mqs.orderservice.controller;

import com.mqs.orderservice.constants.OrderStatus;
import com.mqs.orderservice.dto.Order;
import com.mqs.orderservice.dto.OrderEvent;
import com.mqs.orderservice.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping("/create")
    public String createOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus(OrderStatus.PENDING);
        orderEvent.setMessage("Order is in pending state");
        orderEvent.setOrder(order);

        orderProducer.publish(orderEvent);

        return "Order sent to the RMQ queues for processing...";
    }
}
