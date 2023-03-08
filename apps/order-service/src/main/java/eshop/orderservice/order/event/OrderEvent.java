package eshop.orderservice.order.event;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public sealed class OrderEvent extends BaseEvent permits OrderCreatedEvent, OrderPaidEvent {

}