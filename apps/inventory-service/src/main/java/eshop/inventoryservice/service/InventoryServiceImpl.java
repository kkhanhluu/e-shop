package eshop.inventoryservice.service;

import eshop.api.exceptions.NotFoundException;
import eshop.inventoryservice.entities.ProductInventory;
import eshop.inventoryservice.model.UpdateInventoryRequest;
import eshop.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    @Override
    public Optional<ProductInventory> getInventoryByProductId(UUID productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @Override
    public ProductInventory updateInventoryByProductId(UpdateInventoryRequest request) {
        ProductInventory inventory = inventoryRepository.findByProductId(request.getProductId())
                .orElseThrow(NotFoundException::new);
        inventory.setQuantityOnHand(request.getQuantityOnHand());

        inventoryRepository.save(inventory);
        return inventory;
    }
}