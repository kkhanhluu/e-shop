package eshop.orderservice.order.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class BaseEvent {
    protected UUID aggregateId;
}