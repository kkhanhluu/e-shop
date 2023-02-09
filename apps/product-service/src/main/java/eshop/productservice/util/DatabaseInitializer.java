package eshop.productservice.util;

import com.github.javafaker.Faker;
import eshop.productservice.entities.Product;
import eshop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {
  private final ProductRepository productRepository;

  private final Faker faker = new Faker();

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (productRepository.count() > 0) {
      return;
    }
    List<Product> products = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      products.add(Product.builder()
        .id(UUID.randomUUID())
        .description(faker.commerce().material())
        .name(faker.commerce().productName())
        .price(new BigDecimal(faker.commerce().price(0, 1000)))
        .build());
    }
    productRepository.saveAll(products);
  }
}