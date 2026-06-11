package com.orderflow.orderflowsystem.infrastructure.security.kafka.producer;

import com.orderflow.orderflowsystem.application.event.OrderCreatedEvent;
import com.orderflow.orderflowsystem.application.event.OrderEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderEventPublisher implements OrderEventPublisher {


    private static final String TOPIC = "order-created";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public KafkaOrderEventPublisher(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void publishOrderCreated(OrderCreatedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.orderId().toString(),
                event);

    }
}
