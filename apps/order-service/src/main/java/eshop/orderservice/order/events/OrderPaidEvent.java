package eshop.orderservice.order.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class OrderPaidEvent extends OrderEvent {
}