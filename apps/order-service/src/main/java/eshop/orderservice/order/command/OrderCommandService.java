package eshop.orderservice.order.command;

import eshop.orderservice.order.command.commands.CreateOrderCommand;

import java.util.UUID;

public interface OrderCommandService {
    UUID createOrder(CreateOrderCommand createOrderCommand);
    void processPaymentResponse(UUID orderId, boolean isPaymentSuccessful);
}