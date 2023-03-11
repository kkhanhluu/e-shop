package eshop.orderservice.order.saga;

import eshop.orderservice.order.aggregate.OrderAggregate;

import java.util.Map;

public interface OrderStateMachineService {
    void sendOrderStateMachineEvent(OrderAggregate orderAggregate, OrderStateMachineEvent stateMachineEvent);
    void sendOrderStateMachineEvent(OrderAggregate orderAggregate, OrderStateMachineEvent stateMachineEvent, Map<String, String> additionalHeaders);
}