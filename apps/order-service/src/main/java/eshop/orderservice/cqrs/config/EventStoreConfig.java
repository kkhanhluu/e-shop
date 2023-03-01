package eshop.orderservice.cqrs.config;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventStoreConfig {
    @Value("${eventstore.connection-string}")
    private String eventStoreConnectionString;
    public static final String ORDER_CREATED_EVENT_NAME = "ORDER_CREATED_EVENT";
    public static final String ORDER_STREAM = "ORDER_STREAM";

    @Bean
    public EventStoreDBClient eventStoreDBClusterClient() {
        EventStoreDBClientSettings eventStoreDBClientSettings = EventStoreDBConnectionString.parseOrThrow(
                eventStoreConnectionString);
        EventStoreDBClient eventStoreDBClient = EventStoreDBClient.create(eventStoreDBClientSettings);
        return eventStoreDBClient;
    }
}