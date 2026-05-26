package com.orderflow.orderflowsystem.entrypoint.user;

import com.orderflow.orderflowsystem.application.user.CreateUserUseCase;
import com.orderflow.orderflowsystem.domain.user.UserRequest;
import com.orderflow.orderflowsystem.domain.user.UserResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {


    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid UserRequest request){

            return createUserUseCase.execute(request);

    }


}
