package com.orderflow.orderflowsystem.entrypoint.order;

import com.orderflow.orderflowsystem.application.order.CreateOrderUseCase;
import com.orderflow.orderflowsystem.domain.order.CreateOrderRequest;
import com.orderflow.orderflowsystem.domain.order.CreateOrderResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public CreateOrderResponse createOrder(
            @RequestBody @Valid CreateOrderRequest request
    ) {

        return createOrderUseCase.execute(request);
    }

}
