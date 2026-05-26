package com.orderflow.orderflowsystem.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateOrderResponse(

        Long orderId,
        OrderStatus status,
        BigDecimal totalAmount,
        LocalDateTime createdAt


) {

}
