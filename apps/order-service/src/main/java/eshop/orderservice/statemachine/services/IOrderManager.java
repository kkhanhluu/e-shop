package eshop.orderservice.statemachine.services;

import java.util.UUID;

public interface IOrderManager {
    void processPaymentResponse(UUID orderId, boolean isPaymentSuccessful);
}