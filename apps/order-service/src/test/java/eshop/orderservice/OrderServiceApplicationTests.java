package eshop.orderservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class OrderServiceApplicationTests {

	@Container
	static PostgreSQLContainer postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"));
	static GenericContainer eventStoreDBContainer = new GenericContainer(DockerImageName.parse("eventstore/eventstore:22.10.1-alpha-arm64v8"));

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", postgresContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", postgresContainer::getPassword);

		String eventStoreConnectionString = "esdb://%s:2113?tls=false".formatted(eventStoreDBContainer.getHost());
		dynamicPropertyRegistry.add("eventstore.connection-string", () -> eventStoreConnectionString);
	}

	@BeforeAll
	static void beforeAll() {
		postgresContainer.start();
		eventStoreDBContainer.start();
	}

	@Test
	void contextLoads() {
	}
}