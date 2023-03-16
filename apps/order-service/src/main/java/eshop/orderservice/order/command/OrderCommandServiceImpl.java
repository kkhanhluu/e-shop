package eshop.orderservice.order.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eshop.api.exceptions.NotFoundException;
import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.command.commands.*;
import eshop.orderservice.order.event.OrderEvent;
import eshop.orderservice.order.saga.OrderStateMachineConfig;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import eshop.orderservice.order.saga.OrderStateMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {
    @Qualifier("orderEventStore")
    private final EventStore<OrderAggregate, OrderEvent> eventStore;
    private final OrderStateMachineService orderStateMachineService;
    private final ObjectMapper objectMapper;

    @Override
    public UUID handle(CreateOrderCommand command) {
        OrderAggregate orderAggregate = new OrderAggregate(command.orderId());
        Map<String, String> messageHeaders = new HashMap<>();
        messageHeaders.put(OrderStateMachineConfig.ORDER_USER_ID_HEADER, command.userId().toString());
        try {
            messageHeaders.put(OrderStateMachineConfig.ORDER_LINES_HEADER, objectMapper.writeValueAsString(command.orderLineItems()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        orderStateMachineService.sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.CREATE, messageHeaders);

        return orderAggregate.getId();
    }

    @Override
    public void handle(PayOrderSuccessCommand command) {
        OrderAggregate orderAggregate = eventStore.get(command.orderId()).orElseThrow(NotFoundException::new);
        orderStateMachineService.sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.PAYMENT_SUCCESS);
        OrderAggregate paidOrderAggregate = eventStore.get(command.orderId()).orElseThrow(NotFoundException::new);
        orderStateMachineService.sendOrderStateMachineEvent(paidOrderAggregate, OrderStateMachineEvent.VALIDATE_ORDER);
    }

    @Override
    public void handle(PayOrderFailedCommand command) {
        OrderAggregate orderAggregate = eventStore.get(command.orderId()).orElseThrow(NotFoundException::new);
        orderStateMachineService.sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.PAYMENT_FAILED);
    }

    @Override
    public void handle(ValidateOrderSuccessCommand command) {
        OrderAggregate orderAggregate = eventStore.get(command.orderId()).orElseThrow(NotFoundException::new);
        orderStateMachineService.sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.VALIDATION_PASSED);

        OrderAggregate validatedOrderAggregate = eventStore.get(command.orderId()).orElseThrow(NotFoundException::new);
        orderStateMachineService.sendOrderStateMachineEvent(validatedOrderAggregate, OrderStateMachineEvent.ALLOCATE_ORDER);
    }

    @Override
    public void handle(ValidateOrderFailedCommand command) {
        OrderAggregate orderAggregate = eventStore.get(command.orderId()).orElseThrow(NotFoundException::new);
        orderStateMachineService.sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.VALIDATION_FAILED);
    }
}