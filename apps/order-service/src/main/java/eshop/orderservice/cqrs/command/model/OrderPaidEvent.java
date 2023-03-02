package eshop.orderservice.cqrs.command.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public final class OrderPaidEvent extends OrderDomainEvent {
    private UUID orderId;
}