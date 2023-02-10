package eshop.inventoryservice.controller;

import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.model.UpdateInventoryRequest;
import eshop.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class ProductInventoryController {
    private final InventoryService inventoryService;
    @GetMapping(value = "/product/{productId}", produces = "application/json")
    public ResponseEntity<ProductInventory> getInventoryByProductId(@PathVariable("productId") String productId) {
        return inventoryService.getInventoryByProductId(UUID.fromString(productId))
                .map(inventory -> ResponseEntity.ok(inventory))
                .orElseGet(() -> new ResponseEntity<>(
                        HttpStatus.NOT_FOUND));
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<ProductInventory> updateInventoryByProductId(@RequestBody UpdateInventoryRequest request) {
        ProductInventory productInventory = inventoryService.updateInventoryByProductId(request);
        return ResponseEntity.ok(productInventory);
    }
}