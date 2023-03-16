package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderAllocationFailedEvent extends OrderEvent {
    public OrderAllocationFailedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}