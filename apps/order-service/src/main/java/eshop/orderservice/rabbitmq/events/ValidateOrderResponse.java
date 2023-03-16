package eshop.orderservice.rabbitmq.events;

import lombok.Data;

import java.util.UUID;

@Data
public class ValidateOrderResponse {
    private UUID orderId;
    private boolean isValid;
}