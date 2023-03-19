package eshop.orderservice.order.saga.actions;

import eshop.api.exceptions.NotFoundException;
import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.event.OrderEvent;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.OrderStateMachineConfig;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import eshop.orderservice.rabbitmq.events.CompensatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AllocateOrderFailedAction implements Action<OrderStatus, OrderStateMachineEvent> {
    @Qualifier("orderEventStore")
    private final EventStore<OrderAggregate, OrderEvent> eventStore;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void execute(StateContext<OrderStatus, OrderStateMachineEvent> context) {
        String orderId = (String) context.getMessage().getHeaders().get(OrderStateMachineConfig.ORDER_ID_HEADER);
        OrderAggregate orderAggregate = eventStore.get(UUID.fromString(orderId)).orElseThrow(NotFoundException::new);

        orderAggregate.allocateOrderFailed();
        eventStore.appendEvents(orderAggregate);

        rabbitTemplate.convertAndSend("exchange.eshop", "compensate-payment",  CompensatePaymentRequest.builder().orderId(UUID.fromString(orderId)).build());
    }
}