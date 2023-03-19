package eshop.orderservice.order.query.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class OrderLine {
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    Order order;
    @Id
    private UUID id;
    private UUID productId;
    private BigDecimal productPrice;
    private Integer quantity;
}