package eshop.paymentservice.payment.service;

import eshop.api.exceptions.NotFoundException;
import eshop.constants.RabbitMQConstants;
import eshop.paymentservice.api.request.CompensatePaymentRequest;
import eshop.paymentservice.api.request.CreatePaymentRequest;
import eshop.paymentservice.payment.Payment;
import eshop.paymentservice.payment.repository.PaymentRepository;
import eshop.paymentservice.rabbitmq.events.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final RabbitTemplate rabbitTemplate;
    private final PaymentRepository paymentRepository;

    public boolean createPayment(CreatePaymentRequest createPaymentRequest) {
        boolean isSuccessful = Math.random() < 0.95;
        if (isSuccessful) {
            Payment payment = Payment.builder().orderId(createPaymentRequest.getOrderId().toString()).userId(
                    createPaymentRequest.getUserId().toString()).value(createPaymentRequest.getValue()).isCompensated(false).build();
            paymentRepository.save(payment);
        }

        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.PAYMENT_RESPONSE_KEY,
                new PaymentResponse(createPaymentRequest.getOrderId(), isSuccessful));
        return isSuccessful;
    }

    public void compensatePayment(CompensatePaymentRequest request) {
        Payment payment = paymentRepository.findPaymentByOrderId(request.getOrderId().toString())
                .orElseThrow(NotFoundException::new);
        payment.setIsCompensated(true);
        System.out.println("payment = " + payment);
        paymentRepository.save(payment);
    }
}