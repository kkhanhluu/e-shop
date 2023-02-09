package eshop.productservice.service;

import eshop.productservice.entities.Product;
import eshop.productservice.model.CreateProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
  Optional<Product> findProductById(UUID id);
  Page<Product> getAllProducts(Pageable pageable);
  Product createProduct(CreateProductDTO createProductDTO);
  void deleteProduct(UUID uuid);
}