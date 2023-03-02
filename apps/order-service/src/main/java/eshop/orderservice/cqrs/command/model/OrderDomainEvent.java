package eshop.orderservice.cqrs.command.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
sealed public class OrderDomainEvent permits OrderCreatedEvent, OrderPaidEvent {
   protected UUID orderId;
}