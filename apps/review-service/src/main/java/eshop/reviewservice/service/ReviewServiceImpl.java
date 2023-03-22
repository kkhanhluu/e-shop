package eshop.reviewservice.service;

import eshop.reviewservice.entities.Review;
import eshop.reviewservice.mapper.ReviewMapper;
import eshop.reviewservice.model.AverageRatingForProduct;
import eshop.reviewservice.model.ReviewDTO;
import eshop.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final MongoTemplate mongoTemplate;

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

    @Override
    public double getAverageRatingForProduct(String productId) {
        MatchOperation filterReviews = match(new Criteria("productId").is(productId));
        GroupOperation sumRating = group("productId").sum("rate").as("sumRate");
        GroupOperation averageRating = group("_id").avg("sumRate").as("average");
        LimitOperation limitToOnlyFirstDoc = limit(1);
        Aggregation aggregation = Aggregation.newAggregation(filterReviews, sumRating, averageRating, limitToOnlyFirstDoc);
        AggregationResults<AverageRatingForProduct> result = mongoTemplate.aggregate(aggregation, "reviews",
                AverageRatingForProduct.class);
        result.forEach(i -> System.out.println("i = " + i));
        return result.getMappedResults().get(0).getAverage();
    }
}