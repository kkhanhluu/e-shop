package eshop.reviewservice.mapper;

import eshop.reviewservice.entities.Review;
import eshop.reviewservice.model.ReviewDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper {
    Review reviewDTOToReview(ReviewDTO reviewDTO);
}