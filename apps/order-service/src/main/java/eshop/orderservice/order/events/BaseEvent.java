package eshop.orderservice.order.events;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BaseEvent {
    private UUID aggregateId;
}