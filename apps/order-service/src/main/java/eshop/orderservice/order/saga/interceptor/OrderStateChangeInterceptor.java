package eshop.orderservice.order.saga.interceptor;

import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderStatus, OrderStateMachineEvent> {

    @Override
    public void preStateChange(State<OrderStatus, OrderStateMachineEvent> state, Message<OrderStateMachineEvent> message,
                               Transition<OrderStatus, OrderStateMachineEvent> transition,
                               StateMachine<OrderStatus, OrderStateMachineEvent> stateMachine,
                               StateMachine<OrderStatus, OrderStateMachineEvent> rootStateMachine) {
//        Optional.of(message)
//                .flatMap(msg -> Optional.ofNullable(
//                        (String) msg.getHeaders().getOrDefault(StateMachineConfig.ORDER_ID_HEADER, -1L)))
//                .ifPresent(orderId -> {
//                    OrderDomainEvent orderDomainEvent = generateOrderDomainEventFromOrderStatus(
//                            UUID.fromString(orderId), state.getId());
//                    eventStoreService.appendOrderEvents(orderDomainEvent);
////                    Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow();
////                    order.setStatus(state.getId());
////                    orderRepository.saveAndFlush(order);
//                });
        System.out.println("Pre state changed");
    }

//    private OrderDomainEvent generateOrderDomainEventFromOrderStatus(UUID orderId, OrderStatus status) {
//        switch(status) {
//            case PAID: {
//                OrderPaidEvent event = OrderPaidEvent.builder().orderId(orderId).build();
//                return event;
//            }
//            default:
//                throw new IllegalArgumentException("Invalid order status");
//        }
//    }
}