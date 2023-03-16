package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderAllocatedEvent extends OrderEvent {
    public OrderAllocatedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}