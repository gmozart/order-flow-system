package com.orderflow.orderflowsystem.infrastructure.security.kafka.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "order-flow-consumer-group"
    )
    public void consume(String  event) {

        System.out.println(
                "EVENT RECEIVED =======> " + event
        );
    }
}
