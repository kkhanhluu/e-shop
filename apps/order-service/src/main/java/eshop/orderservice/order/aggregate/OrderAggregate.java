package eshop.orderservice.order.aggregate;

import eshop.orderservice.core.aggregate.RootAggregate;
import eshop.orderservice.order.query.entity.OrderLine;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.events.OrderCreatedEvent;
import eshop.orderservice.order.events.OrderEvent;
import lombok.Data;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
public class OrderAggregate extends RootAggregate<OrderEvent> {
    private UUID userId;
    private Set<OrderLine> orderLineItems;
    private OrderStatus status;

    public OrderAggregate(UUID aggregateId) {
        super(aggregateId);
    }

    private OrderAggregate() {}

    @Override
    public void when(OrderEvent event) {
        if (Objects.requireNonNull(event) instanceof OrderCreatedEvent orderCreatedEvent) {
            handle(orderCreatedEvent);
        } else {
            throw new IllegalArgumentException("Event cannot be null");
        }
    }

    public OrderCreatedEvent createOrder(UUID userId, Set<OrderLine> orderLineItems) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(this.id, userId, orderLineItems);

        this.apply(orderCreatedEvent);

        return orderCreatedEvent;
    }

    public static String getStreamId(UUID orderId) {
        return "Order-%s".formatted(orderId);
    }

    public static OrderAggregate getEmptyOrder() {
        return new OrderAggregate();
    }

    private void handle(OrderCreatedEvent orderCreatedEvent) {
        id = orderCreatedEvent.getAggregateId();
        userId = orderCreatedEvent.getUserId();
        orderLineItems = orderCreatedEvent.getOrderLineItems();
        status = OrderStatus.CREATED;
    }
}