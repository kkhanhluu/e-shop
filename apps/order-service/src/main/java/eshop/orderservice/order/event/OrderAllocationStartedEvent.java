package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderAllocationStartedEvent extends OrderEvent {
    public OrderAllocationStartedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}