package eshop.paymentservice.api.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CompensatePaymentRequest {
    private UUID orderId;
}