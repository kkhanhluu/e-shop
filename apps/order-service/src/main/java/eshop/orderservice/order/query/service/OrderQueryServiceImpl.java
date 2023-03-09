package eshop.orderservice.order.query.service;

import eshop.orderservice.order.query.entity.Order;
import eshop.orderservice.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{
    private final OrderRepository orderRepository;

    @Override
    public Order getOrderById(UUID orderId) {
        Optional<Order> result = orderRepository.findById(orderId);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Order not found with id: %s".formatted(orderId));
        }
        return result.get();
    }
}