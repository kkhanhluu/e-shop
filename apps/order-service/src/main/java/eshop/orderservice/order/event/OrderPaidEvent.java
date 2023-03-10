package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(UUID orderId) {
        this.setAggregateId(orderId);
    }
}