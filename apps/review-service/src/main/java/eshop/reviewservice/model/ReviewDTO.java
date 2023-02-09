package eshop.reviewservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {
    private String productId;
    private String text;
    private String userId;
}