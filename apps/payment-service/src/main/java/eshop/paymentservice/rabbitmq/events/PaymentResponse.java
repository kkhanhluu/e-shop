package eshop.paymentservice.rabbitmq.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PaymentResponse {
    private UUID orderId;
    private boolean isSuccessful;
}