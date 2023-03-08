package eshop.orderservice.order.query.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class OrderLine {
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID productId;
    private BigDecimal productPrice;
    private Integer quantity;
}