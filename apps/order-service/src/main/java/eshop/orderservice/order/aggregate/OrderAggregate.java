package eshop.orderservice.order.aggregate;

import eshop.orderservice.core.aggregate.RootAggregate;
import eshop.orderservice.order.event.OrderPaidEvent;
import eshop.orderservice.order.event.OrderPaymentRejectedEvent;
import eshop.orderservice.order.query.entity.OrderLine;
import eshop.orderservice.order.query.entity.OrderStatus;
import eshop.orderservice.order.event.OrderCreatedEvent;
import eshop.orderservice.order.event.OrderEvent;
import lombok.Data;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
public class OrderAggregate extends RootAggregate<OrderEvent> {
    private UUID userId;
    private Set<OrderLine> orderLineItems;
    private OrderStatus status;

    public OrderAggregate(UUID aggregateId) {
        super(aggregateId);
        this.status = OrderStatus.NEW;
    }

    private OrderAggregate() {}

    @Override
    public void when(OrderEvent event) {
        if (Objects.requireNonNull(event) instanceof OrderCreatedEvent orderCreatedEvent) {
            handle(orderCreatedEvent);
        } else if (Objects.requireNonNull(event) instanceof OrderPaidEvent orderPaidEvent) {
            handle(orderPaidEvent);
        } else if (Objects.requireNonNull(event) instanceof OrderPaymentRejectedEvent orderPaymentRejectedEvent) {
           handle(orderPaymentRejectedEvent);
        } else {
            throw new IllegalArgumentException("Event cannot be null");
        }
    }

    public void createOrder(UUID userId, Set<OrderLine> orderLineItems) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(this.id, userId, orderLineItems);

        this.apply(orderCreatedEvent);
    }

    public void acceptPayment() {
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent(this.id);
        this.apply(orderPaidEvent);
    }

    public void rejectPayment() {
        OrderPaymentRejectedEvent orderPaymentRejectedEvent = new OrderPaymentRejectedEvent(this.id);
        this.apply(orderPaymentRejectedEvent);
    }

    public static String getStreamId(UUID orderId) {
        return "Order-%s".formatted(orderId);
    }

    public static OrderAggregate getEmptyOrder() {
        return new OrderAggregate();
    }

    private void handle(OrderCreatedEvent orderCreatedEvent) {
        id = orderCreatedEvent.getAggregateId();
        userId = orderCreatedEvent.getUserId();
        orderLineItems = orderCreatedEvent.getOrderLineItems();
        status = OrderStatus.CREATED;
    }

    private void handle(OrderPaidEvent orderPaidEvent) {
        status = OrderStatus.PAID;
    }

    private void handle(OrderPaymentRejectedEvent orderPaymentRejectedEvent) {
        status = OrderStatus.PAYMENT_EXCEPTION;
    }
}