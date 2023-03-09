package eshop.orderservice.order.command;

import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.command.commands.CreateOrderCommand;
import eshop.orderservice.order.event.OrderEvent;
import eshop.orderservice.order.query.entity.Order;
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
    public UUID createOrder(CreateOrderCommand command) {
        OrderAggregate orderAggregate = new OrderAggregate(command.orderId());
        orderAggregate.createOrder(command.userId(), command.orderLineItems());
        eventStore.appendEvents(orderAggregate);

        sendOrderStateMachineEvent(orderAggregate.getId(), orderAggregate.getStatus(), OrderStateMachineEvent.CREATE);

        return orderAggregate.getId();
    }

    @Override
    public void processPaymentResponse(UUID orderId, boolean isPaymentSuccessful) {
        Order order = orderQueryService.getOrderById(orderId);
        if (isPaymentSuccessful) {
            sendOrderStateMachineEvent(orderId, order.getStatus(), OrderStateMachineEvent.PAYMENT_SUCCESS);
            Order paidOrder = orderQueryService.getOrderById(orderId);
            sendOrderStateMachineEvent(orderId, order.getStatus(), OrderStateMachineEvent.VALIDATE_ORDER);
        } else {
            sendOrderStateMachineEvent(orderId, order.getStatus(), OrderStateMachineEvent.PAYMENT_FAILED);
        }
    }


    private void sendOrderStateMachineEvent(UUID machineId, OrderStatus status, OrderStateMachineEvent stateMachineEvent) {
        StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine = build(machineId, status);
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(stateMachineEvent)
                .setHeader(OrderStateMachineConfig.ORDER_ID_HEADER, machineId.toString())
                .build())).subscribe();
    }

    private StateMachine<OrderStatus, OrderStateMachineEvent> build(UUID machineId, OrderStatus status) {
        StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine = stateMachineFactory.getStateMachine(machineId);
        stateMachine.stopReactively().block();
        stateMachine.getStateMachineAccessor().doWithAllRegions(stateMachineAccessor -> {
            stateMachineAccessor.addStateMachineInterceptor(orderStateChangeInterceptor);
            stateMachineAccessor.resetStateMachineReactively(
                    new DefaultStateMachineContext<>(status, null, null, null, null)).block();
        });
        stateMachine.startReactively().block();
        return stateMachine;
    }
}