package eshop.inventoryservice.repository;

import eshop.inventoryservice.entities.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<ProductInventory, UUID> {
    Optional<ProductInventory> findByProductId(UUID productId);
}