package com.orderflow.orderflowsystem.application.user;

import com.orderflow.orderflowsystem.domain.user.User;
import com.orderflow.orderflowsystem.domain.user.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetAuthenticatedUserUseCase {

    public UserResponse execute() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        User user = (User) authentication.getPrincipal();

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }



}
