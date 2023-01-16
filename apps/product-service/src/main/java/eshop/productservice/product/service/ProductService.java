package eshop.productservice.product.service;

import eshop.productservice.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
  Optional<Product> findProductById(UUID id);

  Page<Product> getAllProducts(Pageable pageable);
}
