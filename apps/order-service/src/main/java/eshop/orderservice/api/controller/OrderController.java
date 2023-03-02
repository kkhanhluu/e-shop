package eshop.orderservice.api.controller;

import eshop.orderservice.api.request.CreateOrderRequest;
import eshop.orderservice.cqrs.command.service.OrderQueryService;
import eshop.orderservice.entities.Order;
import eshop.orderservice.cqrs.command.service.OrderCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity createOrder(@Valid @RequestBody CreateOrderRequest request) throws URISyntaxException {
        UUID orderId = UUID.randomUUID();
        orderCommandService.createOrder(orderId, request.getUserId(), request.getOrderLineItems());
        return ResponseEntity.created(new URI("api/order/%s".formatted(orderId))).build();
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Order> getOrderById(@PathVariable("orderId") UUID orderId) {
        Order order = orderQueryService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}