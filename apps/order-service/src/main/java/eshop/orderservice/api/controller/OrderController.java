package eshop.orderservice.api.controller;

import eshop.orderservice.order.query.entity.Order;
import eshop.orderservice.order.query.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderQueryService orderQueryService;

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Order> getOrderById(@PathVariable("orderId") UUID orderId) {
        Order order = orderQueryService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}