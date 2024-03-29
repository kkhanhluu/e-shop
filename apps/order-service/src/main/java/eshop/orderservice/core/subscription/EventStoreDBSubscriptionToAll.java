package eshop.orderservice.core.subscription;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ResolvedEvent;
import com.eventstore.dbclient.Subscription;
import com.eventstore.dbclient.SubscriptionListener;
import eshop.orderservice.core.event.EventTypeMapper;
import eshop.orderservice.core.serialization.EventSerializer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.support.RetryTemplate;

import java.util.Optional;

@RequiredArgsConstructor
public class EventStoreDBSubscriptionToAll {
    private final EventStoreDBClient eventStoreDBClient;
    private final Logger logger = LoggerFactory.getLogger(EventStoreDBSubscriptionToAll.class);
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RetryTemplate retryTemplate = RetryTemplate.builder()
            .infiniteRetry()
            .exponentialBackoff(100, 2, 5000)
            .build();
    private final SubscriptionListener listener = new SubscriptionListener() {
        @Override
        public void onEvent(Subscription subscription, ResolvedEvent event) {
            try {
                handleEvent(event);
            } catch (Throwable e) {
                logger.error("Error while handling event", e);
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onError(Subscription subscription, Throwable throwable) {
            logger.error("Subscription error", throwable);
            throw new RuntimeException(throwable);
        }
    };
    private boolean isRunning;
    private Subscription subscription;

    public void subscribeToAll() {
        try {
            retryTemplate.execute(context -> {
                logger.info("Event store DB subscription to all started");
                subscription = eventStoreDBClient.subscribeToAll(listener).get();
                return null;
            });
            isRunning = true;
        } catch (Throwable e) {
            logger.error("Error while starting subscription", e);
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        subscription.stop();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    private void handleEvent(ResolvedEvent resolvedEvent) {
        Optional<Class> eventClass = EventTypeMapper.toClass(resolvedEvent.getEvent().getEventType());
        Optional eventData = eventClass.flatMap(c -> EventSerializer.deserialize(c, resolvedEvent));
        if (eventData.isEmpty()) {
            if (!resolvedEvent.getEvent().getEventType().startsWith("$")) {
                logger.error("Cannot deserialize event with id: %s, %s".formatted(resolvedEvent.getEvent().getEventId(),
                        resolvedEvent.getEvent().getEventType()));
            }
            return;
        }
        applicationEventPublisher.publishEvent(eventData.get());
    }
}