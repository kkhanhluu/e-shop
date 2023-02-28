package eshop.orderservice.statemachine;

import eshop.orderservice.entities.OrderStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvent> {
    public static final String ORDER_ID_HEADER = "ORDER_ID_HEADER";
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderStatus.CREATED)
                .states(EnumSet.allOf(OrderStatus.class))
                .end(OrderStatus.DELIVERED)
                .end(OrderStatus.DELIVERY_EXCEPTION)
                .end(OrderStatus.VALIDATION_EXCEPTION)
                .end(OrderStatus.ALLOCATION_EXCEPTION)
                .end(OrderStatus.PAYMENT_EXCEPTION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions.withExternal().source(OrderStatus.CREATED).target(OrderStatus.PAYMENT_PENDING).event(OrderEvent.PAYMENT_INIT)
                .and().withExternal().source(OrderStatus.PAYMENT_PENDING).target(OrderStatus.PAID).event(OrderEvent.PAYMENT_SUCCESS)
                .and().withExternal().source(OrderStatus.PAYMENT_PENDING).target(OrderStatus.PAYMENT_EXCEPTION).event(OrderEvent.PAYMENT_FAILED)
                .and().withExternal().source(OrderStatus.PAID).target(OrderStatus.VALIDATION_PENDING).event(OrderEvent.VALIDATE_ORDER)
                .and().withExternal().source(OrderStatus.VALIDATION_PENDING).target(OrderStatus.VALIDATED).event(OrderEvent.VALIDATION_PASSED)
                .and().withExternal().source(OrderStatus.VALIDATION_PENDING).target(OrderStatus.VALIDATION_EXCEPTION).event(OrderEvent.VALIDATION_FAILED)
                .and().withExternal().source(OrderStatus.VALIDATED).target(OrderStatus.BEING_DELIVERED).event(OrderEvent.DELIVERY_ORDER)
                .and().withExternal().source(OrderStatus.BEING_DELIVERED).target(OrderStatus.DELIVERED).event(OrderEvent.DELIVERY_SUCCESS)
                .and().withExternal().source(OrderStatus.BEING_DELIVERED).target(OrderStatus.DELIVERY_EXCEPTION).event(OrderEvent.DELIVERY_FAILED);
    }
}