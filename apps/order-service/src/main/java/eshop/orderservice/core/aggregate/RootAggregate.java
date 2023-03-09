package eshop.orderservice.core.aggregate;

import eshop.orderservice.core.event.BaseEvent;
import eshop.orderservice.core.exception.InvalidEventException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public abstract class RootAggregate<Event extends BaseEvent> {
    protected UUID id;
    protected long version;
    protected final Queue<Event> uncommittedEvents = new LinkedList<>();

    public RootAggregate(UUID id) {
        this.id = id;
    }

    public abstract void when(final Event event);

    public void apply(final Event event) {
        this.validateEvent(event);
        when(event);
        uncommittedEvents.add(event);
        this.version++;
    }

    public void load(final List<Event> events) {
        events.forEach(this::apply);
    }

    private void validateEvent(final Event event) {
        System.out.println("event = " + event.getAggregateId() + " - " + this.id);
        if (Objects.isNull(event) || !event.getAggregateId().equals(this.id)) {
            throw new InvalidEventException(event.toString());
        }
    }

    public Object[] dequeueUncommittedEvens() {
        Object[] dequeuedEvents = uncommittedEvents.toArray();
        uncommittedEvents.clear();
        return dequeuedEvents;
    }
}