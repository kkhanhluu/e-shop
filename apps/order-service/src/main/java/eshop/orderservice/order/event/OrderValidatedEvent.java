package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderValidatedEvent extends OrderEvent {
    public OrderValidatedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}