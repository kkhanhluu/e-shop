package eshop.orderservice.messagequeue.handlers;

import eshop.orderservice.messagequeue.RabbitMQConfig;
import eshop.orderservice.messagequeue.events.PaymentResponse;
import eshop.orderservice.order.command.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentResponseListener {
    private final OrderCommandService orderManager;
    @RabbitListener(queues = RabbitMQConfig.PAYMENT_RESPONSE_QUEUE)
    public void listen(@Payload PaymentResponse paymentResponse) {
        final UUID orderId = paymentResponse.getOrderId();
        orderManager.processPaymentResponse(orderId, paymentResponse.isSuccessful());
    }
}