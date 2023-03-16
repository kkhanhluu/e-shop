package eshop.inventoryservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderLine {
    private UUID id;
    private UUID productId;
    private BigDecimal productPrice;
    private Integer quantity;
}