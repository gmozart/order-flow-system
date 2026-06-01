package com.orderflow.orderflowsystem.application.user;

import com.orderflow.orderflowsystem.domain.exception.BusinessException;
import com.orderflow.orderflowsystem.domain.user.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse execute(UserRequest request) {

        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new BusinessException("Email already exists");
                });

        User user = new User(
                null,
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                Role.CUSTOMER
        );

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

}
