package eshop.orderservice.order.saga.actions;

import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class CreateOrderAction implements Action<OrderStatus, OrderStateMachineEvent> {
    @Override
    public void execute(StateContext<OrderStatus, OrderStateMachineEvent> context) {

    }
}