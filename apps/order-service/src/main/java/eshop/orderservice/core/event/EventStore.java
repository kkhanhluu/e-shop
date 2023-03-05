package eshop.orderservice.core.event;

import com.eventstore.dbclient.*;
import eshop.orderservice.core.aggregate.RootAggregate;
import eshop.orderservice.core.serialization.EventSerializer;
import eshop.orderservice.order.events.BaseEvent;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class EventStore<Entity extends RootAggregate, Event extends BaseEvent> {
    private final EventStoreDBClient eventStoreDBClient;
    private final Function<UUID, String> mapToStreamId;
    private final Supplier<Entity> getEmptyEntity;

    public WriteResult appendEvents(Entity entity) {
        String streamId = mapToStreamId.apply(entity.getId());
        Stream<EventData> eventDataStream = Arrays.stream(entity.dequeueUncommittedEvens())
                .map(EventSerializer::serialize);
        try {
            return eventStoreDBClient.appendToStream(streamId, eventDataStream.iterator()).get();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Entity> get(UUID id) {
        String streamId = mapToStreamId.apply(id);
        Optional<List<Event>> events = getEvents(streamId);
        if (events.isEmpty()) {
            return Optional.empty();
        }
        Entity current = getEmptyEntity.get();
        current.setId(id);
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