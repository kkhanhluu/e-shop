package eshop.orderservice.order.events;

import eshop.orderservice.core.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public sealed class OrderEvent extends BaseEvent permits OrderCreatedEvent, OrderPaidEvent {
}