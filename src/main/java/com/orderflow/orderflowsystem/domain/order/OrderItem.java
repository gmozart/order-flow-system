package com.orderflow.orderflowsystem.domain.order;

import com.orderflow.orderflowsystem.domain.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private Integer quantity;

    private BigDecimal unitPrice;

    public OrderItem() {
    }

    public OrderItem(
            Long id,
            Order order,
            Product product,
            Integer quantity,
            BigDecimal unitPrice
    ) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

}