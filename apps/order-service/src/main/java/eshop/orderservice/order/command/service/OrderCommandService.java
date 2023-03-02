package eshop.orderservice.order.command.service;

import eshop.orderservice.order.command.CreateOrderCommand;

import java.util.UUID;

public interface OrderCommandService {
    UUID handle(CreateOrderCommand createOrderCommand);
}