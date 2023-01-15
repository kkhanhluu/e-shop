package eshop.productservice.product.controller;

import com.github.javafaker.Faker;
import eshop.productservice.product.model.Product;
import eshop.productservice.product.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
  private final Faker faker = new Faker();
  @Autowired
  MockMvc mockMvc;
  @MockBean
  ProductServiceImpl productService;

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
    BDDMockito.given(productService.findProductById((UUID.randomUUID()))).willReturn(Optional.of(product));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/product/" + UUID.randomUUID().toString())
      .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
  }
  @Test
  void getProductByIdFailed() throws Exception {
    BDDMockito.given(productService.findProductById((UUID.randomUUID()))).willReturn(Optional.empty());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/product/" + UUID.randomUUID().toString())
      .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
