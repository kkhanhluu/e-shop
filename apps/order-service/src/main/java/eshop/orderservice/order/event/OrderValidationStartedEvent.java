package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderValidationStartedEvent extends OrderEvent {

    public OrderValidationStartedEvent(UUID aggregateId) {
        this.setAggregateId(aggregateId);
    }
}