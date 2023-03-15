package eshop.orderservice.order.command.commands;

import java.util.UUID;

public record ValidateOrderSuccessCommand(UUID orderId) {
}