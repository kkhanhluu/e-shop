package eshop.orderservice.cqrs.command.service;

import com.eventstore.dbclient.EventStoreDBClient;
import eshop.orderservice.entities.Order;

import java.util.UUID;

public class OrderQueryServiceImpl implements OrderQueryService {
    private final EventStoreService eventStoreService;

    @Override
    public Order getOrderById(UUID orderId) {

    }
}