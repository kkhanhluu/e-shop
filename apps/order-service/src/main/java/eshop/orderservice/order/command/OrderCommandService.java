package eshop.orderservice.order.command;

import eshop.orderservice.order.command.commands.CreateOrderCommand;

import java.util.UUID;

public interface OrderCommandService {
    UUID handle(CreateOrderCommand createOrderCommand);
    void processPaymentResponse(UUID orderId, boolean isPaymentSuccessful);
}