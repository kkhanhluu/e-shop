package eshop.orderservice.order.aggregate;

import eshop.orderservice.core.aggregate.RootAggregate;
import eshop.orderservice.order.event.*;
import eshop.orderservice.order.query.entity.OrderLine;
import eshop.orderservice.order.query.entity.OrderStatus;
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

    private OrderAggregate() {
    }

    public static String getStreamId(UUID orderId) {
        return "Order-%s".formatted(orderId);
    }

    public static OrderAggregate getEmptyOrder() {
        return new OrderAggregate();
    }

    @Override
    public void when(OrderEvent event) {
        if (Objects.requireNonNull(event) instanceof OrderCreatedEvent orderCreatedEvent) {
            id = orderCreatedEvent.getAggregateId();
            userId = orderCreatedEvent.getUserId();
            orderLineItems = orderCreatedEvent.getOrderLineItems();
            status = OrderStatus.CREATED;
        } else if (Objects.requireNonNull(event) instanceof OrderPaidEvent orderPaidEvent) {
            status = OrderStatus.PAID;
        } else if (Objects.requireNonNull(event) instanceof OrderPaymentRejectedEvent orderPaymentRejectedEvent) {
            status = OrderStatus.PAYMENT_EXCEPTION;
        } else if (Objects.requireNonNull(event) instanceof OrderValidationStartedEvent orderValidationStartedEvent) {
            status = OrderStatus.VALIDATION_PENDING;
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

    public void validateOrder() {
        OrderValidationStartedEvent orderValidationStartedEvent = new OrderValidationStartedEvent(this.id);
        this.apply(orderValidationStartedEvent);
    }

    public void validateOrderSuccess() {
        OrderValidatedEvent orderValidatedEvent = new OrderValidatedEvent(this.id);
        this.apply(orderValidatedEvent);
    }

    public void validateOrderFailed() {
        OrderValidationFailedEvent orderValidationFailedEvent = new OrderValidationFailedEvent(this.id);
        this.apply(orderValidationFailedEvent);
    }
}