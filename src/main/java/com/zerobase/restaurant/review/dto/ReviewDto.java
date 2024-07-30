package com.zerobase.restaurant.review.dto;

import com.zerobase.restaurant.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String reviewContent;
    private Integer rating;
    private String username;
    private String storeName;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .reviewContent(review.getReviewContent())
                .rating(review.getRating())
                .username(review.getCustomer().getUsername())
                .storeName(review.getStore().getStoreName())
                .build();
    }
}
