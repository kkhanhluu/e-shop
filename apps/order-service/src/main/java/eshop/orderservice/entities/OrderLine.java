package eshop.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class OrderLine {
    @ManyToOne
    @JsonIgnore
    Order order;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID productId;
    private BigDecimal productPrice;
    private Integer quantity;
}