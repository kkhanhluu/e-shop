package eshop.productservice.product.controller;


import eshop.productservice.product.model.Product;
import eshop.productservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping("/{productId}")
  public ResponseEntity<Product> getProductById(@PathVariable("productId") String productId) {
    return productService.findProductById(UUID.fromString(productId))
      .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
