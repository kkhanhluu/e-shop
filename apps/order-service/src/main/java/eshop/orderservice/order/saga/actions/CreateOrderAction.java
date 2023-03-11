package eshop.orderservice.order.saga.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.event.OrderEvent;
import eshop.orderservice.order.query.entity.OrderLine;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.saga.OrderStateMachineConfig;
import eshop.orderservice.order.saga.OrderStateMachineEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateOrderAction implements Action<OrderStatus, OrderStateMachineEvent> {
    @Qualifier("orderEventStore")
    private final EventStore<OrderAggregate, OrderEvent> eventStore;
    private final ObjectMapper objectMapper;

    @Override
    public void execute(StateContext<OrderStatus, OrderStateMachineEvent> context) {
        try {
            String orderId = (String) context.getMessage().getHeaders().get(OrderStateMachineConfig.ORDER_ID_HEADER);
            String userId = (String) context.getMessage()
                    .getHeaders()
                    .get(OrderStateMachineConfig.ORDER_USER_ID_HEADER);
            Set<OrderLine> orderLines = objectMapper.readValue(
                    (String) context.getMessage().getHeaders().get(OrderStateMachineConfig.ORDER_LINES_HEADER),
                    Set.class);

            OrderAggregate orderAggregate = new OrderAggregate(UUID.fromString(orderId));
            orderAggregate.createOrder(UUID.fromString(userId), orderLines);
            eventStore.appendEvents(orderAggregate);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}