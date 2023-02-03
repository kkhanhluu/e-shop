package eshop.userservice.repository;

import eshop.userservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByClientId(String clientId);
}
