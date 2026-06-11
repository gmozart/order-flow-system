package com.orderflow.orderflowsystem.application.event;

public interface OrderEventPublisher {

    void publishOrderCreated(OrderCreatedEvent event);

}
