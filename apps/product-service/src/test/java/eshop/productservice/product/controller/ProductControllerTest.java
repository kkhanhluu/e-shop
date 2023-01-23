package eshop.productservice.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import eshop.api.http.GlobalControllerExceptionHandler;
import eshop.productservice.product.model.Product;
import eshop.productservice.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ProductController.class)
@Import(GlobalControllerExceptionHandler.class)
class ProductControllerTest {
  private final Faker faker = new Faker();
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private ProductService productService;

  private Product getRandomProduct() {
    return Product.builder()
      .id(UUID.randomUUID())
      .name(faker.commerce().productName())
      .description(faker.commerce().material())
      .price(new BigDecimal(faker.commerce().price(0, 1000)))
      .build();
  }

  @Test
  void getProductByIdSuccessfully() throws Exception {
    Product product = getRandomProduct();
    given(productService.findProductById(product.getId())).willReturn(Optional.of(product));

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/product/{productId}", product.getId()
        .toString()))
      .andReturn()
      .getResponse();

    then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void getProductByIdFailed() throws Exception {
    UUID productId = UUID.randomUUID();
    given(productService.findProductById(productId)).willReturn(Optional.empty());

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/product/{productId}", productId.toString()))
      .andReturn()
      .getResponse();

    then(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
  }

  @Test
  void getAllProducts() throws Exception {
    List<Product> products = new ArrayList<>();
    products.add(getRandomProduct());
    products.add(getRandomProduct());
    products.add(getRandomProduct());
    products.add(getRandomProduct());

    PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));
    given(productService.getAllProducts(pageable)).willReturn(new PageImpl<Product>(products, pageable, products.size()));

    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/product")).andReturn()
      .getResponse();

    then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    then(objectMapper.readValue(response.getContentAsString(), Product[].class)).hasSize(products.size());
  }
}
