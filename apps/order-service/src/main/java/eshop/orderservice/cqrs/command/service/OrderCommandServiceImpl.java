package eshop.orderservice.cqrs.command.service;

import com.eventstore.dbclient.EventData;
import eshop.orderservice.cqrs.command.model.OrderDomainEvent;
import eshop.orderservice.cqrs.config.EventStoreConfig;
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
        OrderDomainEvent.OrderCreatedEvent orderCreatedEvent = OrderDomainEvent.OrderCreatedEvent.builder()
                .orderId(orderId)
                .userId(userId)
                .orderLineItems(orderLineItems)
                .build();

        eventStoreService.appendOrderEvents(orderId, orderCreatedEvent);
    }
}