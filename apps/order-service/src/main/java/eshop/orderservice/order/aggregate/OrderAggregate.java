package eshop.orderservice.order.aggregate;

import eshop.orderservice.core.aggregate.RootAggregate;
import eshop.orderservice.entities.OrderLine;
import eshop.orderservice.entities.OrderStatus;
import eshop.orderservice.order.events.OrderCreatedEvent;
import eshop.orderservice.order.events.OrderEvent;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class OrderAggregate extends RootAggregate<OrderEvent> {
    private final String ORDER_STREAM_PREFIX = "ORDER_STREAM_";

    private UUID userId;
    private Set<OrderLine> orderLineItems;
    private OrderStatus status;

    public OrderAggregate(UUID aggregateId) {
        super(aggregateId);
    }

    @Override
    public void when(OrderEvent event) {
        if (Objects.requireNonNull(event) instanceof OrderCreatedEvent orderCreatedEvent) {
            id = orderCreatedEvent.getAggregateId();
            userId = orderCreatedEvent.getUserId();
            orderLineItems = orderCreatedEvent.getOrderLineItems();
            status = OrderStatus.CREATED;
        } else {
            throw new IllegalArgumentException("Event cannot be null");
        }
    }

    @Override
    public String getStreamId() {
        return ORDER_STREAM_PREFIX + this.id;
    }

    public OrderCreatedEvent createOrder(UUID userId, Set<OrderLine> orderLineItems) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .userId(userId)
                .orderLineItems(orderLineItems)
                .build();
        orderCreatedEvent.setAggregateId(id);

        this.apply(orderCreatedEvent);

        return orderCreatedEvent;
    }
}