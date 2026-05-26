package com.orderflow.orderflowsystem.application.user;

import com.orderflow.orderflowsystem.domain.user.User;
import com.orderflow.orderflowsystem.domain.user.UserRepository;
import com.orderflow.orderflowsystem.domain.user.UserRequest;
import com.orderflow.orderflowsystem.domain.user.UserResponse;
import org.springframework.stereotype.Service;


@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;


    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse execute(UserRequest request) {

        User user = new User(
                null,
                request.name(),
                request.email(),
                request.password()
        );

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

}
