package eshop.orderservice.order.command;

import eshop.orderservice.order.command.commands.*;

import java.util.UUID;

public interface OrderCommandService {
    UUID handle(CreateOrderCommand createOrderCommand);
    void handle(PayOrderSuccessCommand createOrderCommand);
    void handle(PayOrderFailedCommand createOrderCommand);
    void handle(ValidateOrderSuccessCommand validateOrderSuccessCommand);
    void handle(ValidateOrderFailedCommand validateOrderFailedCommand);
    void handle(AllocateOrderSuccessCommand allocateOrderSuccessCommand);
    void handle(AllocateOrderFailedCommand allocateOrderFailedCommand);
}



