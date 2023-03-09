package eshop.orderservice.order.saga.actions;

import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class AllocateOrderAction implements Action<OrderStatus, OrderStateMachineEvent> {
    @Override
    public void execute(StateContext<OrderStatus, OrderStateMachineEvent> stateContext) {
        System.out.println("Should send event to queue: allocate order to inventory service");
    }
}