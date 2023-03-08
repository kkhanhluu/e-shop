package eshop.orderservice.api.request;

import eshop.orderservice.order.query.entity.OrderLine;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class CreateOrderRequest {
    private UUID userId;
    private Set<OrderLine> orderLineItems;
}