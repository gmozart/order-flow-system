package com.orderflow.orderflowsystem.entrypoint.user;

import com.orderflow.orderflowsystem.domain.user.User;
import com.orderflow.orderflowsystem.domain.user.UserRepository;
import com.orderflow.orderflowsystem.domain.user.UserRequest;
import com.orderflow.orderflowsystem.domain.user.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request){

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
