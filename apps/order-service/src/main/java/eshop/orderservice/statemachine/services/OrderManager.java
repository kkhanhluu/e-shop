package eshop.orderservice.statemachine.services;

import eshop.orderservice.entities.Order;
import eshop.orderservice.entities.OrderStatus;
import eshop.orderservice.repository.OrderRepository;
import eshop.orderservice.statemachine.OrderEvent;
import eshop.orderservice.statemachine.StateMachineConfig;
import eshop.orderservice.statemachine.interceptor.OrderStateChangeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderManager implements IOrderManager {
    private final OrderRepository orderRepository;
    private final StateMachineFactory<OrderStatus, OrderEvent> stateMachineFactory;
    private final OrderStateChangeInterceptor orderStateChangeInterceptor;

    @Override
    public void processPaymentResponse(UUID orderId, boolean isPaymentSuccessful) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (isPaymentSuccessful) {
            sendOrderStateMachineEvent(order, OrderEvent.PAYMENT_SUCCESS);
            Order paidOrder = orderRepository.findById(orderId).orElseThrow();
            sendOrderStateMachineEvent(paidOrder, OrderEvent.VALIDATE_ORDER);
        } else {
            sendOrderStateMachineEvent(order, OrderEvent.PAYMENT_FAILED);
        }
    }

    private void sendOrderStateMachineEvent(Order order, OrderEvent stateMachineEvent) {
        StateMachine<OrderStatus, OrderEvent> stateMachine = build(order);
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(stateMachineEvent)
                .setHeader(StateMachineConfig.ORDER_ID_HEADER, order.getId().toString())
                .build())).subscribe();
    }

    private StateMachine<OrderStatus, OrderEvent> build(Order order) {
        StateMachine<OrderStatus, OrderEvent> stateMachine = stateMachineFactory.getStateMachine(order.getId());
        stateMachine.stopReactively().block();
        stateMachine.getStateMachineAccessor().doWithAllRegions(stateMachineAccessor -> {
            stateMachineAccessor.addStateMachineInterceptor(orderStateChangeInterceptor);
            stateMachineAccessor.resetStateMachineReactively(
                    new DefaultStateMachineContext<>(order.getStatus(), null, null, null, null)).block();
        });
        stateMachine.startReactively().block();
        return stateMachine;
    }
}