package eshop.orderservice.order.service;

import lombok.Builder;

import java.util.UUID;

@Builder
public class CompensatePaymentRequest {
    private UUID orderId;
}