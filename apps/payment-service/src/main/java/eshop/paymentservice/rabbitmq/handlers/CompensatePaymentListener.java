package eshop.paymentservice.rabbitmq.handlers;

import eshop.constants.RabbitMQConstants;
import eshop.paymentservice.payment.service.PaymentService;
import eshop.paymentservice.rabbitmq.events.CompensatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompensatePaymentListener {
    private final PaymentService paymentService;

    @RabbitListener(queues = RabbitMQConstants.COMPENSATE_PAYMENT_QUEUE)
    public void listener(@Payload CompensatePaymentRequest request) {
        paymentService.compensatePayment(request.getOrderId());
    }
}