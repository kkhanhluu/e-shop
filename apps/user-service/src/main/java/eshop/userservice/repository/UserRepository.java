package eshop.userservice.repository;

import eshop.userservice.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
  Optional<User> findByUsername(String userName);
  Optional<User> findByEmail(String email);
}
