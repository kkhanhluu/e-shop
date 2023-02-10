package eshop.inventoryservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UpdateInventoryRequest {
    private UUID productId;
    private Integer quantityOnHand;
}