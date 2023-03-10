package eshop.orderservice.rabbitmq.events;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PaymentResponse {
    private UUID orderId;
    private boolean isSuccessful;
}