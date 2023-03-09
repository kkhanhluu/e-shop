package eshop.paymentservice.service;

import eshop.constants.RabbitMQConstants;
import eshop.paymentservice.rabbitmq.events.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final RabbitTemplate rabbitTemplate;

    public void createPayment(UUID orderId) {
        boolean isSuccessful = Math.random() < 0.95;
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.PAYMENT_RESPONSE_KEY,
                new PaymentResponse(orderId, isSuccessful));
    }
}