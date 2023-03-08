package eshop.orderservice.order.command.service;

import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.command.CreateOrderCommand;
import eshop.orderservice.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    @Qualifier("orderEventStore")
    private final EventStore<OrderAggregate, OrderEvent> eventStore;

    @Override
    public UUID handle(CreateOrderCommand command) {
        OrderAggregate orderAggregate = new OrderAggregate(command.orderId());
        orderAggregate.createOrder(command.userId(), command.orderLineItems());
        eventStore.appendEvents(orderAggregate);
        return orderAggregate.getId();
    }
}