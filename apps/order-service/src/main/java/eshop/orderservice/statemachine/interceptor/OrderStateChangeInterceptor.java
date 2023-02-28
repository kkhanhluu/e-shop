package eshop.orderservice.statemachine.interceptor;

import eshop.orderservice.entities.Order;
import eshop.orderservice.entities.OrderStatus;
import eshop.orderservice.repository.OrderRepository;
import eshop.orderservice.statemachine.OrderEvent;
import eshop.orderservice.statemachine.StateMachineConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderStatus, OrderEvent> {
    private final OrderRepository orderRepository;

    @Override
    public void preStateChange(State<OrderStatus, OrderEvent> state, Message<OrderEvent> message,
                               Transition<OrderStatus, OrderEvent> transition,
                               StateMachine<OrderStatus, OrderEvent> stateMachine,
                               StateMachine<OrderStatus, OrderEvent> rootStateMachine) {
        Optional.of(message)
                .flatMap(msg -> Optional.ofNullable(
                        (String) msg.getHeaders().getOrDefault(StateMachineConfig.ORDER_ID_HEADER, -1L)))
                .ifPresent(orderId -> {
                    Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
                    order.setStatus(state.getId());
                    orderRepository.saveAndFlush(order);
                });
    }
}