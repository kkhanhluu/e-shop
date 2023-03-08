package eshop.orderservice.order.query.service;

import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.query.entity.Order;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.event.OrderEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{
    @Qualifier("orderEventStore")
    private final EventStore<OrderAggregate, OrderEvent> eventStore;

    @Override
    public Order getOrderById(UUID orderId) {
        Optional<OrderAggregate> result = eventStore.get(orderId);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Order not found with id: %s".formatted(orderId));
        }
        OrderAggregate orderAggregate = result.get();
        return Order.builder()
                .id(orderAggregate.getId())
                .orderLineItems(orderAggregate.getOrderLineItems())
                .userId(orderAggregate.getUserId())
                .build();
    }
}