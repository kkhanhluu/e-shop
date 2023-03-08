package eshop.orderservice.order.event;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BaseEvent {
    private UUID aggregateId;
}