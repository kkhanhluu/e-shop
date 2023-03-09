package eshop.orderservice.order.command;

import eshop.orderservice.order.command.commands.CreateOrderCommand;
import eshop.orderservice.order.command.commands.PayOrderFailedCommand;
import eshop.orderservice.order.command.commands.PayOrderSuccessCommand;

import java.util.UUID;

public interface OrderCommandService {
    UUID handle(CreateOrderCommand createOrderCommand);
    void handle(PayOrderSuccessCommand createOrderCommand);
    void handle(PayOrderFailedCommand createOrderCommand);
}