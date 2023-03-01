package eshop.orderservice.cqrs.command.service;

import eshop.orderservice.cqrs.command.model.OrderDomainEvent;
import eshop.orderservice.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderQueryServiceImpl implements OrderQueryService {
    private final EventStoreService eventStoreService;

    @Override
    public Order getOrderById(UUID orderId) {
        Order order = Order.getEmptyOrder();
        List<OrderDomainEvent> events = eventStoreService.getOrderDomainEvents(orderId);
        for (OrderDomainEvent event : events) {
            order.when(event);
        }
        return order;
    }
}