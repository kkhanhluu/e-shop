package eshop.orderservice.order.event;

import eshop.orderservice.order.query.entity.OrderLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderCreatedEvent extends OrderEvent {
    @Getter
    @Setter
    private UUID userId;
    @Getter
    @Setter
    private Set<OrderLine> orderLineItems;

    public OrderCreatedEvent(UUID aggregateId, UUID userId, Set<OrderLine> orderLineItems) {
        this.setAggregateId(aggregateId);
        this.userId = userId;
        this.orderLineItems = orderLineItems;
    }
}