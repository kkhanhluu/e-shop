package eshop.inventoryservice.rabbitmq.event;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ValidateOrderResponse {
    private UUID orderId;
    private boolean isValid;
}