package eshop.orderservice.core.serialization;

import com.eventstore.dbclient.ResolvedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eshop.orderservice.core.event.EventTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public final class EventSerializer {
    public static final ObjectMapper mapper = new JsonMapper();
    public static final Logger logger = LoggerFactory.getLogger(EventSerializer.class);

    public static <Event> Optional<Event> deserialize(ResolvedEvent resolvedEvent) {
        Optional<Class> eventClass = EventTypeMapper.toClass(resolvedEvent.getEvent().getEventType());
        if (eventClass.isEmpty()) {
            return Optional.empty();
        }
        return deserialize(eventClass.get(), resolvedEvent);
    }

    public static <Event> Optional<Event> deserialize(Class<Event> eventClass, ResolvedEvent resolvedEvent) {
        try {
            Event result = mapper.readValue(resolvedEvent.getEvent().getEventData(), eventClass);
            if (result == null) {
                return Optional.empty();
            }
            return Optional.of(result);
        } catch (IOException e) {
            logger.warn("Error while deserializing event %s".formatted(resolvedEvent.getEvent().getEventType()), e);
            throw new RuntimeException(e);
        }
    }
}