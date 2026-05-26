package com.orderflow.orderflowsystem.entrypoint.product;

import com.orderflow.orderflowsystem.application.product.CreateProductUseCase;
import com.orderflow.orderflowsystem.domain.product.ProductRequest;
import com.orderflow.orderflowsystem.domain.product.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping
    public ProductResponse createProduct(@RequestBody @Valid ProductRequest request) {

        return createProductUseCase.execute(request);
    }




}
