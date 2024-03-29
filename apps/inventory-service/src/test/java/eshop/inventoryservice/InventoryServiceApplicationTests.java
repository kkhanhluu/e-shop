package eshop.inventoryservice;

import eshop.constants.RabbitMQConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class InventoryServiceApplicationTests {
    @Container
    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"));
    @Container
    static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer(
            DockerImageName.parse("rabbitmq:3.11.10-management-alpine")).withVhost("/")
            .withQueue(RabbitMQConstants.ALLOCATE_ORDER_QUEUE).withQueue(RabbitMQConstants.VALIDATE_ORDER_QUEUE);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgresContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgresContainer::getPassword);

        dynamicPropertyRegistry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
        dynamicPropertyRegistry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
    }

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
        rabbitMQContainer.start();
    }

    @Test
    void contextLoads() {
    }

}