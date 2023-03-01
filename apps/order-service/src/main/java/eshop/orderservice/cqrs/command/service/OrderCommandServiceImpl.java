package eshop.orderservice.cqrs.command.service;

import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import eshop.orderservice.cqrs.command.model.OrderCreatedEvent;
import eshop.orderservice.entities.OrderLine;
import eshop.orderservice.cqrs.config.EventStoreConfig;
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
                .orderId(orderId)
                .userId(userId)
                .orderLineItems(orderLineItems)
                .build();
        EventData eventData = EventData.builderAsJson(EventStoreConfig.ORDER_CREATED_EVENT_NAME, orderCreatedEvent)
                .build();
        eventStoreService.appendOrderEvents(orderId, eventData);
    }
}