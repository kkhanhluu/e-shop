package eshop.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "allocate_order_outbox")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllocateOrderOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID orderId;
    private boolean isSuccessful;
}