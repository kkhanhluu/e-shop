package eshop.orderservice.messagequeue.events;

import eshop.orderservice.order.query.entity.OrderLine;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ValidateOrderRequest {
    private UUID orderID;
    private Set<OrderLine> orderLines;
}