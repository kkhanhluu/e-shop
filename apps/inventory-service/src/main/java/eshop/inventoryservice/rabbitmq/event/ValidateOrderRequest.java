package eshop.inventoryservice.rabbitmq.event;

import eshop.inventoryservice.model.OrderLine;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ValidateOrderRequest {
    private UUID orderID;
    private Set<OrderLine> orderLines;
}