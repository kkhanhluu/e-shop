package eshop.inventoryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.model.UpdateInventoryRequest;
import eshop.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductInventoryControllerTest {
    @Container
    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"));
    @Autowired
    private ObjectMapper objectMapper;
    private final Faker faker = new Faker();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InventoryRepository inventoryRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgresContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
    }

    @Test
    void getInventoryByProductId() throws Exception {
        // Arrange
        ProductInventory inventory = getRandomProductInventory();
        inventoryRepository.save(inventory);

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/inventory/product/{productId}", inventory.getProductId()))
                .andReturn()
                .getResponse();

        // Assert
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        ProductInventory returnedInventory = objectMapper.readValue(response.getContentAsString(),
                ProductInventory.class);
        then(returnedInventory.getProductId()).isEqualTo(inventory.getProductId());
        then(returnedInventory.getQuantityOnHand()).isEqualTo(inventory.getQuantityOnHand());
    }


    @Test
    void updateInventoryByProductId() throws Exception {
        // Arrange
        ProductInventory inventory = getRandomProductInventory();
        inventoryRepository.save(inventory);
        UpdateInventoryRequest request = UpdateInventoryRequest.builder().productId(inventory.getProductId()).quantityOnHand(inventory.getQuantityOnHand() * 2).build();

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/inventory").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse();

        // Assert
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        ProductInventory returnedInventory = objectMapper.readValue(response.getContentAsString(),
                ProductInventory.class);
        then(returnedInventory.getProductId()).isEqualTo(inventory.getProductId());
        then(returnedInventory.getQuantityOnHand()).isEqualTo(request.getQuantityOnHand());
    }

    private ProductInventory getRandomProductInventory() {
        return ProductInventory.builder()
                .productId(UUID.randomUUID())
                .quantityOnHand(faker.random().nextInt(10, 100))
                .build();
    }
}