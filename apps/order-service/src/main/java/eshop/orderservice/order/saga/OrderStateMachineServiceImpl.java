package eshop.orderservice.order.saga;

import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.interceptor.OrderStateChangeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderStateMachineServiceImpl implements OrderStateMachineService {
    private final StateMachineFactory<OrderStatus, OrderStateMachineEvent> stateMachineFactory;
    private final OrderStateChangeInterceptor orderStateChangeInterceptor;

    @Override
    public void sendOrderStateMachineEvent(OrderAggregate orderAggregate, OrderStateMachineEvent stateMachineEvent) {
        StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine = build(orderAggregate);
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(stateMachineEvent)
                .setHeader(OrderStateMachineConfig.ORDER_ID_HEADER, orderAggregate.getId().toString())
                .build())).subscribe();
    }

    @Override
    public void sendOrderStateMachineEvent(OrderAggregate orderAggregate, OrderStateMachineEvent stateMachineEvent,
                                           Map<String, String> additionalHeaders) {
        StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine = build(orderAggregate);
        MessageBuilder<OrderStateMachineEvent> messageBuilder = MessageBuilder.withPayload(
                        stateMachineEvent)
                .setHeader(OrderStateMachineConfig.ORDER_ID_HEADER, orderAggregate.getId().toString());
        additionalHeaders.forEach((key, value) -> {
            messageBuilder.setHeader(key, value);
        });
        stateMachine.sendEvent(Mono.just(messageBuilder.build())).subscribe();
    }

    private StateMachine<OrderStatus, OrderStateMachineEvent> build(OrderAggregate orderAggregate) {
        StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine = stateMachineFactory.getStateMachine(
                orderAggregate.getId());
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