package com.orderflow.orderflowsystem.entrypoint.handler;

import java.time.LocalDateTime;

public record ErrorResponse(
        Integer status,
        String message,
        LocalDateTime timestamp
) {
}
