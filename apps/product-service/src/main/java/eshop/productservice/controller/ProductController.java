package eshop.productservice.controller;

import eshop.api.exceptions.NotFoundException;
import eshop.productservice.entities.Product;
import eshop.productservice.model.CreateProductDTO;
import eshop.productservice.service.ProductService;
import jakarta.validation.Valid;
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

  @GetMapping(value ="/{productId}", produces = "application/json")
  public Product getProductById(@PathVariable("productId") String productId) {
    return productService.findProductById(UUID.fromString(productId))
            .orElseThrow(() -> new NotFoundException("No found product for productId: " + productId));
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

  @PostMapping(produces = "application/json")
  public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductDTO request) {
    Product product = productService.createProduct(request);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{productId}", produces = "application/json")
  public ResponseEntity deleteProduct(@PathVariable("productId") String productId) {
    productService.deleteProduct(UUID.fromString(productId));
    return ResponseEntity.noContent().build();
  }
}