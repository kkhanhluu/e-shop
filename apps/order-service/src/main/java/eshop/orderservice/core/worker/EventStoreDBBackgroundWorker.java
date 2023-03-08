package eshop.orderservice.core.worker;

import com.eventstore.dbclient.EventStoreDBClient;
import eshop.orderservice.core.subscription.EventStoreDBSubscriptionToAll;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.SmartLifecycle;

@RequiredArgsConstructor
public class EventStoreDBBackgroundWorker implements SmartLifecycle {
    private final EventStoreDBClient eventStoreDBClient;
    private final ApplicationEventPublisher applicationEventPublisher;
    private EventStoreDBSubscriptionToAll subscription;

    @Override
    public void start() {
        subscription = new EventStoreDBSubscriptionToAll(eventStoreDBClient, applicationEventPublisher);
        subscription.subscribeToAll();
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        subscription.stop();
    }

    @Override
    public boolean isRunning() {
        if (subscription == null) {
            return false;
        }
        return subscription.isRunning();
    }

    @Override
    public boolean isAutoStartup() {
       return true;
    }
}