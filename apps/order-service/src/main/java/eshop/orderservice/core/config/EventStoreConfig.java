package eshop.orderservice.core.config;

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

    @Bean
    public EventStoreDBClient eventStoreDBClusterClient() {
        EventStoreDBClientSettings eventStoreDBClientSettings = EventStoreDBConnectionString.parseOrThrow(
                eventStoreConnectionString);
        EventStoreDBClient eventStoreDBClient = EventStoreDBClient.create(eventStoreDBClientSettings);
        return eventStoreDBClient;
    }
}