package com.orderflow.orderflowsystem.entrypoint.auth;

import com.orderflow.orderflowsystem.application.auth.LoginUseCase;
import com.orderflow.orderflowsystem.application.user.GetAuthenticatedUserUseCase;
import com.orderflow.orderflowsystem.domain.auth.LoginRequest;
import com.orderflow.orderflowsystem.domain.auth.LoginResponse;
import com.orderflow.orderflowsystem.domain.user.UserResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    public AuthController(LoginUseCase loginUseCase, GetAuthenticatedUserUseCase getAuthenticatedUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.getAuthenticatedUserUseCase = getAuthenticatedUserUseCase;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody @Valid LoginRequest request
    ) {

        return loginUseCase.execute(request);
    }

    @GetMapping("/me")
    public UserResponse me() {

        return getAuthenticatedUserUseCase.execute();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {

        return "Área administrativa";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String customer() {

        return "Área cliente";
    }



}
