package eshop.inventoryservice.repository;

import eshop.inventoryservice.entities.AllocateOrderOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AllocateOrderOutboxRepository extends JpaRepository<AllocateOrderOutbox, UUID> {
}