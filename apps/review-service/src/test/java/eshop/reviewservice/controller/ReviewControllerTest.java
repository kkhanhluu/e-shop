package eshop.reviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import eshop.reviewservice.entities.Review;
import eshop.reviewservice.model.ReviewDTO;
import eshop.reviewservice.repository.ReviewRepository;
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
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ReviewControllerTest {
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:6.0.3"));
    private final Faker faker = new Faker();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();
    }

    @Test
    void shouldCreateReviewSuccessfully() throws Exception {
        // Arrange
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .productId(UUID.randomUUID().toString())
                .userId(UUID.randomUUID().toString())
                .text(faker.lorem().paragraph())
                .build();

        // Act
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDTO))).andReturn().getResponse();

        // Assert
        Review returnedReview = objectMapper.readValue(response.getContentAsString(), Review.class);
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        then(returnedReview.getUserId()).isEqualTo(reviewDTO.getUserId());
        then(returnedReview.getProductId()).isEqualTo(reviewDTO.getProductId());
        then(returnedReview.getText()).isEqualTo(reviewDTO.getText());
    }

    @Test
    void getListOfReviewsByProduct() throws Exception {
        // Arrange
        UUID productId = UUID.randomUUID();
        reviewRepository.save(getRandomReview(productId));
        reviewRepository.save(getRandomReview(productId));

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/reviews/product/{productId}", productId)).andReturn()
                .getResponse();

        // Assert
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(objectMapper.readValue(response.getContentAsString(), Review[].class).length).isEqualTo(2);
    }

    private Review getRandomReview(UUID productId) {
        return Review.builder()
                .productId(productId.toString())
                .userId(UUID.randomUUID().toString())
                .text(faker.lorem().paragraph())
                .build();
    }
}