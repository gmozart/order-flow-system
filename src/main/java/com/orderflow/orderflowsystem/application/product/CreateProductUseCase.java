package com.orderflow.orderflowsystem.application.product;

import com.orderflow.orderflowsystem.domain.product.Product;
import com.orderflow.orderflowsystem.domain.product.ProductRepository;
import com.orderflow.orderflowsystem.domain.product.ProductRequest;
import com.orderflow.orderflowsystem.domain.product.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse execute(ProductRequest request) {

        Product product = new Product(
                null,
                request.name(),
                request.price(),
                request.stockQuantity()
        );

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStockQuantity()
        );
    }

}
