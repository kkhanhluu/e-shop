package eshop.paymentservice.api.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreatePaymentRequest {
    private UUID userId;
    private BigDecimal value;
    private UUID orderId;
}