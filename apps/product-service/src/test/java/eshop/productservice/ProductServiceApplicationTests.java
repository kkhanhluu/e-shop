package eshop.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class ProductServiceApplicationTests {
	@Container
	static PostgreSQLContainer postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"));

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", postgresContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", postgresContainer::getPassword);
	}

	@Test
	void contextLoads() {
	}

}