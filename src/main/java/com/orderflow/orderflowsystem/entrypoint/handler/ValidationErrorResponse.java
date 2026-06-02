package com.orderflow.orderflowsystem.entrypoint.handler;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponse(

        LocalDateTime timestamp,
        Integer status,
        Map<String, String> errors


) {
}
