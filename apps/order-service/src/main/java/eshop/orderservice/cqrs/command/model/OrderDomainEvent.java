package eshop.orderservice.cqrs.command.model;

import eshop.orderservice.entities.OrderLine;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public sealed class OrderDomainEvent permits OrderCreatedEvent, OrderPaidEvent {
    UUID orderId;
}

@Data
@Builder
final class OrderCreatedEvent extends OrderDomainEvent {
    private UUID userId;
    private Set<OrderLine> orderLineItems;
}

@Data
@Builder
final class OrderPaidEvent extends OrderDomainEvent {
}