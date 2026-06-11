package com.orderflow.orderflowsystem.infrastructure.security.kafka.producer;

import com.orderflow.orderflowsystem.application.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private static final String TOPIC = "order-created";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderEventProducer(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderCreatedEvent event) {
        kafkaTemplate.send(
                TOPIC,
                event.orderId().toString(),
                event
        );
    }
}