package eshop.orderservice.rabbitmq.events;

import eshop.orderservice.order.query.entity.OrderLine;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AllocateOrderRequest {
    private UUID orderId;
    private Set<OrderLine> orderLines;
}