package eshop.reviewservice.service;

import eshop.reviewservice.entities.Review;
import eshop.reviewservice.model.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Review createReview(ReviewDTO reviewDTO);

    Page<Review> findReviewByProductId(String productId, Pageable pageable);
}