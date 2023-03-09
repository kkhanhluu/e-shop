package eshop.orderservice.order.saga.actions;

import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<OrderStatus, OrderStateMachineEvent> {
    @Override
    public void execute(StateContext<OrderStatus, OrderStateMachineEvent> stateContext) {
        System.out.println("Should send event to queue to validate order in inventory service");
    }
}