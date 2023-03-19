package eshop.paymentservice.rabbitmq.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CompensatePaymentRequest {
    private UUID orderId;
}