package eshop.orderservice.order.query.service;

import eshop.orderservice.entities.Order;

import java.util.UUID;

public interface OrderQueryService {
    public Order getOrderById(UUID uuid);
}