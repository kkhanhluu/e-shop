package eshop.orderservice.order.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class OrderPaidEvent extends OrderEvent {
}