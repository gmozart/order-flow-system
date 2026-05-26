package com.orderflow.orderflowsystem.domain.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(

        @NotNull(message = "User id is required")
        Long userId,

        @Valid
        @NotEmpty(message = "Order must contain items")
        List<CreateOrderItemRequest> items

) {
}
