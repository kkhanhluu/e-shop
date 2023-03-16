package eshop.orderservice.rabbitmq.events;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AllocateOrderResponse {
    private UUID orderId;
    private boolean isSuccessful;
}