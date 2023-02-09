package eshop.productservice.service;

import eshop.productservice.entities.Product;
import eshop.productservice.mappers.ProductMapper;
import eshop.productservice.model.CreateProductDTO;
import eshop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  @Override
  public Optional<Product> findProductById(UUID id) {
    return productRepository.findById(id);
  }

  @Override
  public Page<Product> getAllProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public Product createProduct(CreateProductDTO createProductDTO) {
    Product product = productMapper.createProductDTOToProduct(createProductDTO);
    this.productRepository.save(product);
    return product;
  }

  @Override
  public void deleteProduct(UUID id) {
    this.productRepository.deleteById(id);
  }
}