package com.mqs.orderservice.producer;

import com.mqs.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OrderProducer.class);

    @Value("${rabbitmq.order.exchange}")
    private String exchange;

    @Value("${rabbitmq.order.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.email.routing.key}")
    private String emailRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(OrderEvent orderEvent) {
        rabbitTemplate.convertAndSend(exchange, routingKey, orderEvent);
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderEvent);

        LOGGER.info("Order event published to both order and email queues => {}", orderEvent.toString());
    }

}
