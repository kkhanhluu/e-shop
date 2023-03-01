package eshop.orderservice.cqrs.command.service;

import eshop.orderservice.entities.Order;

import java.util.UUID;

public interface OrderQueryService {
    Order getOrderById(UUID orderId);
}