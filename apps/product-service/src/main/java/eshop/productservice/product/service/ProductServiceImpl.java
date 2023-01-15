package eshop.productservice.product.service;

import eshop.productservice.product.model.Product;
import eshop.productservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
  private final ProductRepository productRepository;
  @Override
  public Optional<Product> findProductById(UUID id) {
    return productRepository.findById(id);
  }
}
