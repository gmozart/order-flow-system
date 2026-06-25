package com.orderflow.orderflowsystem.infrastructure.kafka.consumer;

import com.orderflow.orderflowsystem.application.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateDltConsumer {

    @KafkaListener(
            topics = "order-created-dlt",
            groupId = "order-flow-dlt-consumer-group"
    )
    public void consume(OrderCreatedEvent event) {
        System.out.println(
                "DLQ MESSAGE RECEIVED =======> " + event
        );
    }
}