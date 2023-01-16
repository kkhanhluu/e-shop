package eshop.productservice.product.controller;


import eshop.productservice.product.model.Product;
import eshop.productservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
      );
  }

  @GetMapping
  public ResponseEntity<Collection<Product>> getAllProducts(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
    Page<Product> pageResult = productService.getAllProducts(pageable);
    if (pageResult.hasContent()) {
      return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(new ArrayList<Product>(), HttpStatus.OK);
    }
  }
}
