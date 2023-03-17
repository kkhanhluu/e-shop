package eshop.paymentservice.controller;

import eshop.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value = "/{orderId}")
    ResponseEntity createPayment(@PathVariable("orderId") UUID orderId) {
        boolean isSuccessful = paymentService.createPayment(orderId);
        return ResponseEntity.ok(isSuccessful);
    }
}