package eshop.reviewservice.repository;

import eshop.reviewservice.entities.Review;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    Page<Review> findByProductId(String productId, Pageable pageable);
}