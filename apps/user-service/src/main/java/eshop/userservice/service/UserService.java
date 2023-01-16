package eshop.userservice.service;

import eshop.userservice.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
