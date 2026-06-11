package com.orderflow.orderflowsystem.infrastructure.security.kafka.consumer;

import com.orderflow.orderflowsystem.application.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "order-flow-consumer-group"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println(
                "EVENT RECEIVED => " + event
        );
    }
}
