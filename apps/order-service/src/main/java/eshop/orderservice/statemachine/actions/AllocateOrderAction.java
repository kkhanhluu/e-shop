package eshop.orderservice.statemachine.actions;

import eshop.orderservice.entities.OrderStatus;
import eshop.orderservice.statemachine.OrderEvent;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class AllocateOrderAction implements Action<OrderStatus, OrderEvent> {
    @Override
    public void execute(StateContext<OrderStatus, OrderEvent> stateContext) {
        System.out.println("Should send event to queue: allocate order to inventory service");
    }
}