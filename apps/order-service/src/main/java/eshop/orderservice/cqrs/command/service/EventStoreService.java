package eshop.orderservice.cqrs.command.service;

import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.WriteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class EventStoreService {
    private final EventStoreDBClient eventStoreDBClient;
    private static final String ORDER_STREAM_PREFIX = "ORDER_STREAM_";

    public WriteResult appendOrderEvents(UUID orderId, EventData eventData)  {
        try {
            return eventStoreDBClient.appendToStream(ORDER_STREAM_PREFIX + orderId, eventData).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public List<E>
}