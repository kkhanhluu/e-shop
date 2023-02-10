package eshop.inventoryservice.service;


import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.model.UpdateInventoryRequest;

import java.util.Optional;
import java.util.UUID;

public interface InventoryService {
    Optional<ProductInventory> getInventoryByProductId(UUID productId);
    ProductInventory updateInventoryByProductId(UpdateInventoryRequest request);
}