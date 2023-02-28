package eshop.orderservice.messagequeue.events;

import eshop.orderservice.entities.OrderLine;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class AllocateOrderRequest {
    private UUID orderId;
    private Set<OrderLine> orderLines;
}