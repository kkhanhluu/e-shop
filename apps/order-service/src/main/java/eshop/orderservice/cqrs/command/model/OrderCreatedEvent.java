package eshop.orderservice.cqrs.command.model;

import eshop.orderservice.entities.OrderLine;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public final class OrderCreatedEvent extends OrderDomainEvent {
    private UUID userId;
    private Set<OrderLine> orderLineItems;
}