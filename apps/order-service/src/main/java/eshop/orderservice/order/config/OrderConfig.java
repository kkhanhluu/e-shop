package eshop.orderservice.order.config;

import com.eventstore.dbclient.EventStoreDBClient;
import eshop.orderservice.core.event.EventStore;
import eshop.orderservice.order.aggregate.OrderAggregate;
import eshop.orderservice.order.event.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class OrderConfig {
    @Bean(name = "orderEventStore")
    @ApplicationScope
    EventStore<OrderAggregate, OrderEvent> orderEventStore(EventStoreDBClient eventStoreDBClient) {
        return new EventStore<>(eventStoreDBClient, OrderAggregate::getStreamId, OrderAggregate::getEmptyOrder);
    }
}