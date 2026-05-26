package com.orderflow.orderflowsystem.application.order;


import com.orderflow.orderflowsystem.domain.exception.BusinessException;
import com.orderflow.orderflowsystem.domain.order.*;
import com.orderflow.orderflowsystem.domain.product.Product;
import com.orderflow.orderflowsystem.domain.product.ProductRepository;
import com.orderflow.orderflowsystem.domain.user.User;
import com.orderflow.orderflowsystem.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public CreateOrderUseCase(
            OrderRepository orderRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public CreateOrderResponse  execute(CreateOrderRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() ->
                        new BusinessException("User not found")
                );

        Order order = new Order(
                null,
                user,
                OrderStatus.CREATED,
                BigDecimal.ZERO,
                LocalDateTime.now()
        );

        Order savedOrder = orderRepository.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CreateOrderItemRequest itemRequest : request.items()) {

            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() ->
                            new BusinessException("Product not found")
                    );

            if (product.getStockQuantity() < itemRequest.quantity()) {
                throw new BusinessException(
                        "Insufficient stock for product: " + product.getName()
                );
            }

            BigDecimal itemTotal =
                    product.getPrice()
                            .multiply(BigDecimal.valueOf(itemRequest.quantity()));

            totalAmount = totalAmount.add(itemTotal);

            OrderItem orderItem = new OrderItem(
                    null,
                    savedOrder,
                    product,
                    itemRequest.quantity(),
                    product.getPrice()
            );

            orderItemRepository.save(orderItem);
        }

        Order updatedOrder = new Order(
                savedOrder.getId(),
                user,
                OrderStatus.CREATED,
                totalAmount,
                savedOrder.getCreatedAt()
        );

        orderRepository.save(updatedOrder);

        return new CreateOrderResponse(
                savedOrder.getId(),
                OrderStatus.CREATED,
                totalAmount,
                savedOrder.getCreatedAt()
        );
    }

}
