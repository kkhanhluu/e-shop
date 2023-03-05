package eshop.orderservice.core.event;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ReadResult;
import com.eventstore.dbclient.ReadStreamOptions;
import eshop.orderservice.core.aggregate.RootAggregate;
import eshop.orderservice.core.serialization.EventSerializer;
import eshop.orderservice.order.events.BaseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class EventStore<Entity extends RootAggregate, Event extends BaseEvent> {
    private final EventStoreDBClient eventStoreDBClient;
    private final Function<UUID, String> mapToStreamId;
    private final Supplier<Entity> getEmptyEntity;

    public Optional<Entity> get(UUID id) {
        String streamId = mapToStreamId.apply(id);
        Optional<List<Event>> events = getEvents(streamId);
        if (events.isEmpty()) {
            return Optional.empty();
        }
        Entity current = getEmptyEntity.get();
        for (Event event : events.get()) {
            current.apply(event);
        }
        return Optional.of(current);
    }

    private Optional<List<Event>> getEvents(String streamId) {
        ReadStreamOptions readOptions = ReadStreamOptions.get().forwards().fromStart();
        ReadResult readResult;
        try {
            readResult = eventStoreDBClient.readStream(streamId, readOptions).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(readResult.getEvents()
                .stream()
                .map(EventSerializer::<Event>deserialize)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList());
    }

}