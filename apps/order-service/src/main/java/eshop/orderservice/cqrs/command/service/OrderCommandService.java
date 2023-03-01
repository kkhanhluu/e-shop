package eshop.orderservice.cqrs.command.service;

import eshop.orderservice.entities.OrderLine;

import java.util.Set;
import java.util.UUID;

public interface OrderCommandService {
    void createOrder(UUID orderId, UUID userId, Set<OrderLine> orderLineItems);
}