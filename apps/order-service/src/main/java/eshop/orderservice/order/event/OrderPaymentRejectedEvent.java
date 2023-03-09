package eshop.orderservice.order.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@ToString
public final class OrderPaymentRejectedEvent extends OrderEvent {
    public OrderPaymentRejectedEvent(UUID orderId) {
        this.setAggregateId(orderId);
    }
}