package eshop.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "outbox")
@Data
public class AllocateOrderOutbox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID orderId;
    private boolean isSuccessful;
}