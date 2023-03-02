package eshop.orderservice.cqrs.command.service;

import com.eventstore.dbclient.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import eshop.orderservice.cqrs.command.model.OrderCreatedEvent;
import eshop.orderservice.cqrs.command.model.OrderDomainEvent;
import eshop.orderservice.cqrs.config.EventStoreConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventStoreService {
    private static final String ORDER_STREAM_PREFIX = "ORDER_STREAM_";
    private final EventStoreDBClient eventStoreDBClient;
    private final ObjectMapper objectMapper;

    public WriteResult appendOrderEvents(OrderDomainEvent domainEvent) {
        EventData eventData = EventData.builderAsJson(EventStoreConfig.ORDER_CREATED_EVENT_NAME, domainEvent)
                .build();
        try {
            return eventStoreDBClient.appendToStream(getOrderStreamId(domainEvent.getOrderId()), eventData).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderDomainEvent> getOrderDomainEvents(UUID orderId) {
        ReadStreamOptions readOptions = ReadStreamOptions.get()
                .forwards()
                .fromStart();
        ReadResult readResult;
        try {
            readResult = eventStoreDBClient.readStream(getOrderStreamId(orderId), readOptions).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return readResult.getEvents().stream().map(resolvedEvent -> {
            RecordedEvent recordedEvent = resolvedEvent.getOriginalEvent();
            try {
                OrderDomainEvent orderDomainEvent = objectMapper.readValue(recordedEvent.getEventData(),
                        OrderCreatedEvent.class);
                System.out.println("orderDomainEvent = " + orderDomainEvent);
                return orderDomainEvent;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    private String getOrderStreamId(UUID orderId) {
        return ORDER_STREAM_PREFIX + orderId.toString();
    }
}