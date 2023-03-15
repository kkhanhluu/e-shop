package eshop.paymentservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class PaymentServiceApplicationTests {

	@Container
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.11.10"));

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
		dynamicPropertyRegistry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
	}

	@BeforeAll
	static void beforeAll() {
		rabbitMQContainer.start();
	}

	@Test
	void contextLoads() {
	}

}