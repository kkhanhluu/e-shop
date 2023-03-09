package eshop.orderservice.order.projection;

import eshop.orderservice.order.event.OrderCreatedEvent;
import eshop.orderservice.order.query.entity.Order;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProjection {
    private final OrderRepository orderRepository;

    @EventListener
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        Order order = Order.builder()
                .id(event.getAggregateId())
                .userId(event.getUserId())
                .status(OrderStatus.CREATED)
                .lastProcessedPosition(event.getLogPosition())
                .build();
        event.getOrderLineItems().forEach(orderLineItem -> {
            order.addOrderLineItem(orderLineItem);
            System.out.println("orderLineItem = " + orderLineItem.getId());
        });
        orderRepository.saveAndFlush(order);
    }
}