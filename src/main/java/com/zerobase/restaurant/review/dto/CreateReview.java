package com.zerobase.restaurant.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CreateReview {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String reviewContent;
        private Integer rating;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reviewId;
        private String reviewContent;
        private Integer rating;
        private String username;
        private String storeName;

        public static Response from(ReviewDto reviewDto) {
            return Response.builder()
                    .reviewId(reviewDto.getReviewId())
                    .reviewContent(reviewDto.getReviewContent())
                    .rating(reviewDto.getRating())
                    .username(reviewDto.getUsername())
                    .storeName(reviewDto.getStoreName())
                    .build();
        }
    }
}
