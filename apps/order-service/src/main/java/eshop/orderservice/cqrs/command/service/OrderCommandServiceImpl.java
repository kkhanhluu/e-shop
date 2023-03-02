package eshop.orderservice.cqrs.command.service;

import eshop.orderservice.cqrs.command.model.OrderCreatedEvent;
import eshop.orderservice.entities.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    private final EventStoreService eventStoreService;
    @Override
    public void createOrder(UUID orderId, UUID userId, Set<OrderLine> orderLineItems) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .userId(userId)
                .orderLineItems(orderLineItems)
                .build();
        orderCreatedEvent.setOrderId(orderId);
        eventStoreService.appendOrderEvents(orderCreatedEvent);
    }
}