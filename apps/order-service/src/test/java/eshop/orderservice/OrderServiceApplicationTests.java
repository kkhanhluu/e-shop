package eshop.orderservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class OrderServiceApplicationTests {

	@Container
	static PostgreSQLContainer postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"));
	@Container
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.11.10"));
	@Container
	static GenericContainer eventStoreDBContainer = new GenericContainer(
			DockerImageName.parse("eventstore/eventstore:22.10.1-alpha-arm64v8"))
			.withEnv("EVENTSTORE_CLUSTER_SIZE", "1")
			.withEnv("EVENTSTORE_RUN_PROJECTIONS", "All")
			.withEnv("EVENTSTORE_START_STANDARD_PROJECTIONS", "true")
			.withEnv("EVENTSTORE_EXT_TCP_PORT", "1113")
			.withEnv("EVENTSTORE_HTTP_PORT", "2113")
			.withEnv("EVENTSTORE_INSECURE", "true")
			.withEnv("EVENTSTORE_ENABLE_EXTERNAL_TCP", "true")
			.withEnv("EVENTSTORE_ENABLE_ATOM_PUB_OVER_HTTP", "true").withExposedPorts(1113, 2113);

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", postgresContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", postgresContainer::getPassword);

		dynamicPropertyRegistry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
		dynamicPropertyRegistry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);

		String eventStoreConnectionString = "esdb://%s:%s?tls=false".formatted(eventStoreDBContainer.getHost(), eventStoreDBContainer.getMappedPort(2113));
		dynamicPropertyRegistry.add("eventstore.connection-string", () -> eventStoreConnectionString);
	}

	@BeforeAll
	static void beforeAll() {
		postgresContainer.start();
		rabbitMQContainer.start();
		eventStoreDBContainer.start();
	}

	@Test
	void contextLoads() {
	}
}