package com.orderflow.orderflowsystem.application.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
        Long orderId,
        String customerEmail,
        BigDecimal totalAmount
) {
}
