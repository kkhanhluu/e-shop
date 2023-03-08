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
        System.out.println("event.getUserId() = " + event.toString());
        Order order = Order.builder()
                .userId(event.getUserId())
                .status(OrderStatus.CREATED)
                .build();
        event.getOrderLineItems().forEach(orderLineItem -> {
            order.addOrderLineItem(orderLineItem);
        });
        orderRepository.saveAndFlush(order);
    }
}