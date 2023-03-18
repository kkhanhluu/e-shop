package eshop.paymentservice.api.controller;

import eshop.paymentservice.api.request.CompensatePaymentRequest;
import eshop.paymentservice.api.request.CreatePaymentRequest;
import eshop.paymentservice.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/")
    ResponseEntity createPayment(@RequestBody CreatePaymentRequest request) {
        boolean isSuccessful = paymentService.createPayment(request);
        return ResponseEntity.ok(isSuccessful);
    }

    @PostMapping(value = "/compensation")
    ResponseEntity compensatePayment(@RequestBody CompensatePaymentRequest request) {
        System.out.println("Compensating payment");
        paymentService.compensatePayment(request);
        return ResponseEntity.ok(true);
    }
}