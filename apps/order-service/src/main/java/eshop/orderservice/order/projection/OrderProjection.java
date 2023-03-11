package eshop.orderservice.order.projection;

import eshop.api.exceptions.NotFoundException;
import eshop.orderservice.order.event.OrderCreatedEvent;
import eshop.orderservice.order.event.OrderPaidEvent;
import eshop.orderservice.order.event.OrderPaymentRejectedEvent;
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
        });
        orderRepository.saveAndFlush(order);
    }

    @EventListener
    void handleOrderPaidEvent(OrderPaidEvent event) {
        Order order = orderRepository.findById(event.getAggregateId()).orElseThrow(NotFoundException::new);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }

    @EventListener
    void handleOrderPaymentRejectedEvent(OrderPaymentRejectedEvent event) {
        Order order = orderRepository.findById(event.getAggregateId()).orElseThrow(NotFoundException::new);
        order.setStatus(OrderStatus.PAYMENT_EXCEPTION);
        orderRepository.save(order);
    }
}