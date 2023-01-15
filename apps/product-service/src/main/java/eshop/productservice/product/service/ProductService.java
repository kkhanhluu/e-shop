package eshop.productservice.product.service;

import eshop.productservice.product.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
  Optional<Product> findProductById(UUID id);
}
