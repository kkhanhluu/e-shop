package eshop.productservice.product.controller;

import com.github.javafaker.Faker;
import eshop.productservice.product.model.Product;
import eshop.productservice.product.service.ProductService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTest {
  private final Faker faker = new Faker();
  @Autowired
  MockMvc mockMvc;
  @MockBean
  ProductService productService;

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
}
