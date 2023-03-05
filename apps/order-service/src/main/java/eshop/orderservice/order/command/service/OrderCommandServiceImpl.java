package eshop.orderservice.order.command.service;

import com.eventstore.dbclient.EventStoreDBClient;
import eshop.orderservice.cqrs.command.service.EventStoreService;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.command.CreateOrderCommand;
import eshop.orderservice.order.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    private final EventStoreDBClient eventStore;
    @Override
    public UUID handle(CreateOrderCommand command) {
        OrderAggregate orderAggregate = new OrderAggregate(command.orderId());
        OrderCreatedEvent orderCreatedEvent = orderAggregate.createOrder(command.userId(), command.orderLineItems());

//        eventStore.appendToStream(orderCreatedEvent.getStreamId(), )
//        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
//                .userId(command.userId())
//                .orderLineItems(command.orderLineItems())
//                .build();
//        orderCreatedEvent.setOrderId(orderId);
//        eventStoreService.appendOrderEvents(orderCreatedEvent);
    }
}