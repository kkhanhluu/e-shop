package eshop.orderservice.order.saga.actions;

import eshop.api.exceptions.NotFoundException;
import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.event.OrderEvent;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.OrderStateMachineConfig;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void execute(StateContext<OrderStatus, OrderStateMachineEvent> context) {
        String orderId = (String) context.getMessage().getHeaders().get(OrderStateMachineConfig.ORDER_ID_HEADER);
        OrderAggregate orderAggregate = eventStore.get(UUID.fromString(orderId)).orElseThrow(NotFoundException::new);

        orderAggregate.allocateOrderFailed();
        eventStore.appendEvents(orderAggregate);
        // TO-DO: implement undo logic to cancel the payment
    }
}