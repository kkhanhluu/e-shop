package eshop.orderservice.statemachine.actions;

import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.statemachine.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<OrderStatus, OrderEvent> {
    @Override
    public void execute(StateContext<OrderStatus, OrderEvent> stateContext) {
        System.out.println("Should send event to queue to validate order in inventory service");
    }
}