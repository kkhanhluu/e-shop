package eshop.orderservice.order.command.service;

import eshop.orderservice.cqrs.command.service.EventStoreService;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    private final EventStoreService eventStoreService;
    @Override
    public UUID handle(CreateOrderCommand command) {
        OrderAggregate orderAggregate = new OrderAggregate(command.orderId());
        orderAggregate.createOrder(command.userId(), command.orderLineItems());

//        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
//                .userId(command.userId())
//                .orderLineItems(command.orderLineItems())
//                .build();
//        orderCreatedEvent.setOrderId(orderId);
//        eventStoreService.appendOrderEvents(orderCreatedEvent);
    }
}