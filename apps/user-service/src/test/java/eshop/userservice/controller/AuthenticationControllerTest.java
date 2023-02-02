package eshop.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import eshop.userservice.model.AuthenticationResponse;
import eshop.userservice.model.LoginRequest;
import eshop.userservice.model.RegisterRequest;
import eshop.userservice.model.User;
import eshop.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
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

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class AuthenticationControllerTest {
    @Container
    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:15.0.1"));
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    private final Faker faker = new Faker();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    }

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
    }

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    void shouldRegisterSuccessfully() throws Exception {
        // Arrange
        User user = User.builder()
                .password(faker.internet().password())
                .username(faker.name().username())
                .email(faker.internet().emailAddress()).build();
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();

        // Act
        MockHttpServletResponse httpResponse = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest))).andReturn().getResponse();

        // Assert
        AuthenticationResponse authenticationResponse = objectMapper.readValue(httpResponse.getContentAsString(),
                AuthenticationResponse.class);
        then(httpResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(authenticationResponse.getId()).isNotNull().isNotBlank();
        then(authenticationResponse.getEmail()).isEqualTo(user.getEmail());
        then(authenticationResponse.getToken()).isNotNull();
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {
        // Arrange
        User user = User.builder()
                .password(faker.internet().password())
                .username(faker.name().username())
                .email(faker.internet().emailAddress()).build();
        LoginRequest loginRequest = LoginRequest.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterRequest.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .password(user.getPassword()).build())));
        MockHttpServletResponse httpResponse = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest))).andReturn().getResponse();

        // Assert
        AuthenticationResponse authenticationResponse = objectMapper.readValue(httpResponse.getContentAsString(),
                AuthenticationResponse.class);
        then(httpResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(authenticationResponse.getId()).isNotNull().isNotBlank();
        then(authenticationResponse.getEmail()).isEqualTo(user.getEmail());
        then(authenticationResponse.getToken()).isNotNull();
    }

    @Test
    void shouldLoginFailed() throws Exception {
        // Arrange
        User user = User.builder()
                .password(faker.internet().password())
                .username(faker.name().username())
                .email(faker.internet().emailAddress()).build();
        userRepository.save(user);
        LoginRequest loginRequest = LoginRequest.builder()
                .email(user.getEmail())
                .password(user.getPassword() + "Invalid password")
                .build();

        // Act
        MockHttpServletResponse httpResponse = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest))).andReturn().getResponse();

        // Assert
        then(httpResponse.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        then(httpResponse.getContentAsString()).isNullOrEmpty();
    }
}