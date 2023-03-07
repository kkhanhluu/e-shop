package eshop.orderservice.order.command;

import eshop.orderservice.order.query.entity.OrderLine;

import java.util.Set;
import java.util.UUID;

public record CreateOrderCommand(UUID orderId, UUID userId, Set<OrderLine> orderLineItems) {
}