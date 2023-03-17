package eshop.orderservice.core.event;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BaseEvent {
    private UUID aggregateId;
    private long logPosition;
    private UUID eventId = UUID.randomUUID();
}