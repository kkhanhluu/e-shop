package eshop.orderservice.order.query.service;

import eshop.orderservice.order.query.entity.Order;

import java.util.UUID;

public interface OrderQueryService {
    public Order getOrderById(UUID uuid);
}