package eshop.reviewservice.service;

import eshop.reviewservice.entities.Review;
import eshop.reviewservice.mapper.ReviewMapper;
import eshop.reviewservice.model.ReviewDTO;
import eshop.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.reviewDTOToReview(reviewDTO);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Page<Review> findReviewByProductId(String productId, Pageable pageable) {
        return reviewRepository.findByProductId(productId, pageable);
    }
}