package eshop.orderservice.order.saga;

import eshop.orderservice.order.query.entity.OrderStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStateMachineEvent> {
    public static final String ORDER_ID_HEADER = "ORDER_ID_HEADER";
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStateMachineEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.NEW)
                .states(EnumSet.allOf(OrderStatus.class))
                .end(OrderStatus.DELIVERED)
                .end(OrderStatus.DELIVERY_EXCEPTION)
                .end(OrderStatus.VALIDATION_EXCEPTION)
                .end(OrderStatus.ALLOCATION_EXCEPTION)
                .end(OrderStatus.PAYMENT_EXCEPTION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStateMachineEvent> transitions) throws Exception {
        transitions.withExternal().source(OrderStatus.NEW).target(OrderStatus.CREATED).event(OrderStateMachineEvent.CREATE)
                .and().withExternal().source(OrderStatus.CREATED).target(OrderStatus.PAYMENT_PENDING).event(
                        OrderStateMachineEvent.PAYMENT_INIT)
                .and().withExternal().source(OrderStatus.PAYMENT_PENDING).target(OrderStatus.PAID).event(
                        OrderStateMachineEvent.PAYMENT_SUCCESS)
                .and().withExternal().source(OrderStatus.PAYMENT_PENDING).target(OrderStatus.PAYMENT_EXCEPTION).event(
                        OrderStateMachineEvent.PAYMENT_FAILED)
                .and().withExternal().source(OrderStatus.PAID).target(OrderStatus.VALIDATION_PENDING).event(
                        OrderStateMachineEvent.VALIDATE_ORDER)
                .and().withExternal().source(OrderStatus.VALIDATION_PENDING).target(OrderStatus.VALIDATED).event(
                        OrderStateMachineEvent.VALIDATION_PASSED)
                .and().withExternal().source(OrderStatus.VALIDATION_PENDING).target(OrderStatus.VALIDATION_EXCEPTION).event(
                        OrderStateMachineEvent.VALIDATION_FAILED)
                .and().withExternal().source(OrderStatus.VALIDATED).target(OrderStatus.BEING_DELIVERED).event(
                        OrderStateMachineEvent.DELIVERY_ORDER)
                .and().withExternal().source(OrderStatus.BEING_DELIVERED).target(OrderStatus.DELIVERED).event(
                        OrderStateMachineEvent.DELIVERY_SUCCESS)
                .and().withExternal().source(OrderStatus.BEING_DELIVERED).target(OrderStatus.DELIVERY_EXCEPTION).event(
                        OrderStateMachineEvent.DELIVERY_FAILED);
    }
}