package eshop.orderservice.api.request;

import eshop.orderservice.entities.OrderLine;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class CreateOrderRequest {
    private UUID userId;
    private Set<OrderLine> orderLineItems;
}