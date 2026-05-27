package com.orderflow.orderflowsystem.entrypoint.auth;

import com.orderflow.orderflowsystem.application.auth.LoginUseCase;
import com.orderflow.orderflowsystem.domain.auth.LoginRequest;
import com.orderflow.orderflowsystem.domain.auth.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        return loginUseCase.execute(request);
    }


}
