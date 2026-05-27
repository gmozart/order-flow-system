package com.orderflow.orderflowsystem.domain.auth;

public record LoginRequest(

        String email,
        String password

) {
}
