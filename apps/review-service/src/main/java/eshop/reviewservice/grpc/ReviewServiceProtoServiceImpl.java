package eshop.reviewservice.grpc;

import eshop.reviewservice.model.ReviewDTO;
import eshop.reviewservice.service.ReviewService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@GrpcService
@RequiredArgsConstructor
public class ReviewServiceProtoServiceImpl extends ReviewServiceGrpc.ReviewServiceImplBase {
    private final ReviewService reviewService;

    @Override
    public void getReviewsByProductId(GetReviewsByProductIdRequest request,
                                      StreamObserver<GetReviewsByProductIdResponse> responseObserver) {
        PageRequest pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("id"));
        Page<eshop.reviewservice.entities.Review> reviews = this.reviewService.findReviewByProductId(
                request.getProductId(), pageable);

        GetReviewsByProductIdResponse.Builder responseBuilder = GetReviewsByProductIdResponse.newBuilder()
                .setHasNext(reviews.hasNext())
                .setHasPrevious(reviews.hasPrevious())
                .setTotalElements(reviews.getTotalElements())
                .setTotalPage(reviews.getTotalPages());
        if (reviews.hasContent()) {
            reviews.getContent().forEach(review -> {
                Review reviewResponse = Review.newBuilder()
                        .setProductId(review.getProductId())
                        .setRate(review.getRate())
                        .setText(review.getText())
                        .setId(review.getId().toString())
                        .setCreatedAt(review.getCreatedAt().toString())
                        .build();
                responseBuilder.addReviews(reviewResponse);
            });
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void createReview(CreateReviewRequest request, StreamObserver<Review> responseObserver) {
        eshop.reviewservice.entities.Review review = reviewService.createReview(ReviewDTO.builder()
                .productId(request.getProductId())
                .text(request.getText())
                .rate(request.getRate())
                .build());

        responseObserver.onNext(Review.newBuilder()
                .setProductId(review.getProductId())
                .setRate(review.getRate())
                .setText(review.getText())
                .setId(review.getId().toString())
                .setCreatedAt(review.getCreatedAt().toString())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAverageRatingForProduct(GetAverageRatingForProductRequest request,
                                           StreamObserver<GetAverageRatingForProductResponse> responseObserver) {
        double averageRatingForProduct = reviewService.getAverageRatingForProduct(request.getProductId());
        responseObserver.onNext(
                GetAverageRatingForProductResponse.newBuilder().setRating(averageRatingForProduct).build());
        responseObserver.onCompleted();
    }
}