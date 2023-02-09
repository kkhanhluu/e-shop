package eshop.reviewservice.controller;

import eshop.reviewservice.entities.Review;
import eshop.reviewservice.model.ReviewDTO;
import eshop.reviewservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        Review review = this.reviewService.createReview(reviewDTO);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping(value = "/product/{productId}", produces = "application/json")
    public ResponseEntity<Collection<Review>> getReviewByProductid(@PathVariable("productId") String productId,
                                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Review> reviews = this.reviewService.findReviewByProductId(productId, pageable);
        if (reviews.hasContent()) {
            return new ResponseEntity<>(reviews.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<Review>(), HttpStatus.OK);
    }
}