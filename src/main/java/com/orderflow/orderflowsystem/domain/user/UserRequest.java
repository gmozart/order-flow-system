package com.orderflow.orderflowsystem.domain.user;

public record UserRequest(

        String name,
        String email,
        String password

) {
}
