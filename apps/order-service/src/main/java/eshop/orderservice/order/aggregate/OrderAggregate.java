package eshop.orderservice.order.aggregate;

import eshop.orderservice.core.aggregate.RootAggregate;
import eshop.orderservice.entities.OrderLine;
import eshop.orderservice.entities.OrderStatus;
import eshop.orderservice.order.events.OrderCreatedEvent;
import eshop.orderservice.order.events.OrderEvent;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class OrderAggregate extends RootAggregate<OrderEvent> {
    private UUID userId;
    private Set<OrderLine> orderLineItems;
    private OrderStatus status;

    @Override
    public void when(OrderEvent event) {
        switch(event) {
            case OrderCreatedEvent orderCreatedEvent:
                id = orderCreatedEvent.getAggregateId();
                userId = orderCreatedEvent.getUserId();
                orderLineItems = orderCreatedEvent.getOrderLineItems();
                status = OrderStatus.CREATED;
                break;
            default:
                throw new IllegalArgumentException("Event cannot be null");
        }
    }

    public void createOrder(UUID userId, Set<OrderLine> orderLineItems) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .userId(userId)
                .orderLineItems(orderLineItems)
                .build();
        orderCreatedEvent.setAggregateId(id);

        this.apply(orderCreatedEvent);
    }
}