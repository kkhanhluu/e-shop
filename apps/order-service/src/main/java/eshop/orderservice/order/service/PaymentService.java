package eshop.orderservice.order.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class PaymentService {
    private final String PAYMENT_API_PATH = "/api/payment/compensation";
    @Value("${payment.api-host}")
    private String PAYMENT_API_HOST;
    private final RestTemplate restTemplate;

    public PaymentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void compensatePayment(UUID orderId) {
        CompensatePaymentRequest request = CompensatePaymentRequest.builder().orderId(orderId).build();
        System.out.println("request = " + request);
        this.restTemplate.postForLocation(PAYMENT_API_HOST + PAYMENT_API_PATH, request);
    }
}