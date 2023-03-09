package eshop.orderservice.order.command;

import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.command.commands.CreateOrderCommand;
import eshop.orderservice.order.command.commands.PayOrderFailedCommand;
import eshop.orderservice.order.command.commands.PayOrderSuccessCommand;
import eshop.orderservice.order.event.OrderEvent;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.query.service.OrderQueryService;
import eshop.orderservice.order.saga.OrderStateMachineConfig;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import eshop.orderservice.order.saga.interceptor.OrderStateChangeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderQueryService orderQueryService;
    private final StateMachineFactory<OrderStatus, OrderStateMachineEvent> stateMachineFactory;
    private final OrderStateChangeInterceptor orderStateChangeInterceptor;
    @Qualifier("orderEventStore")
    private final EventStore<OrderAggregate, OrderEvent> eventStore;

    @Override
    public UUID handle(CreateOrderCommand command) {
        OrderAggregate orderAggregate = new OrderAggregate(command.orderId());

        sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.CREATE);

        orderAggregate.createOrder(command.userId(), command.orderLineItems());
        eventStore.appendEvents(orderAggregate);
        return orderAggregate.getId();
    }

    @Override
    public void handle(PayOrderSuccessCommand command) {
        Optional<OrderAggregate> orderAggregateOptional = eventStore.get(command.orderId());
        if (orderAggregateOptional.isEmpty()) {
            throw new IllegalArgumentException("Cannot find order with id: %s".formatted(command.orderId()));
        }
        OrderAggregate orderAggregate = orderAggregateOptional.get();

        sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.PAYMENT_SUCCESS);

        orderAggregate.acceptPayment();
        eventStore.appendEvents(orderAggregate);

        sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.VALIDATE_ORDER);
    }

    @Override
    public void handle(PayOrderFailedCommand command) {
        Optional<OrderAggregate> orderAggregateOptional = eventStore.get(command.orderId());
        if (orderAggregateOptional.isEmpty()) {
            throw new IllegalArgumentException("Cannot find order with id: %s".formatted(command.orderId()));
        }
        OrderAggregate orderAggregate = orderAggregateOptional.get();

        sendOrderStateMachineEvent(orderAggregate, OrderStateMachineEvent.PAYMENT_FAILED);

        orderAggregate.rejectPayment();
    }

    private void sendOrderStateMachineEvent(OrderAggregate orderAggregate, OrderStateMachineEvent stateMachineEvent) {
        System.out.println("send state machine event status = " + orderAggregate.getStatus());
        StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine = build(orderAggregate);
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(stateMachineEvent)
                .setHeader(OrderStateMachineConfig.ORDER_ID_HEADER, orderAggregate.getId().toString())
                .build())).subscribe();
    }

    private StateMachine<OrderStatus, OrderStateMachineEvent> build(OrderAggregate orderAggregate) {
        StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine = stateMachineFactory.getStateMachine(orderAggregate.getId());
        stateMachine.stopReactively().block();
        stateMachine.getStateMachineAccessor().doWithAllRegions(stateMachineAccessor -> {
            stateMachineAccessor.addStateMachineInterceptor(orderStateChangeInterceptor);
            stateMachineAccessor.resetStateMachineReactively(
                    new DefaultStateMachineContext<>(orderAggregate.getStatus(), null, null, null, null)).block();
        });
        stateMachine.startReactively().block();
        return stateMachine;
    }
}