package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderValidationFailedEvent extends OrderEvent {
    public OrderValidationFailedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}