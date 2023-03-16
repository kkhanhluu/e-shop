package eshop.orderservice.order.event;

import eshop.orderservice.core.event.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public sealed class OrderEvent extends BaseEvent permits OrderCreatedEvent, OrderPaidEvent, OrderPaymentRejectedEvent, OrderValidationStartedEvent, OrderValidatedEvent, OrderValidationFailedEvent {
}