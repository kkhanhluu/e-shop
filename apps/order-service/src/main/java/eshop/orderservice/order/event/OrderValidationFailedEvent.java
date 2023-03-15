package eshop.orderservice.order.event;

import java.util.UUID;

public final class OrderValidationFailedEvent extends OrderEvent {
    public OrderValidationFailedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}