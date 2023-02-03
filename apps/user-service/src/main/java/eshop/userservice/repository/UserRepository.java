package eshop.userservice.repository;

import eshop.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUsername(String userName);
  Optional<User> findByEmail(String email);
}
