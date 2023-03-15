package eshop.orderservice.order.event;

import java.util.UUID;

public final class OrderValidatedEvent extends OrderEvent {
    public OrderValidatedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}