package com.zerobase.restaurant.review.service;

import com.zerobase.restaurant.review.dto.CreateReview;
import com.zerobase.restaurant.review.dto.ReviewDto;

public interface ReviewService {

    ReviewDto createReview(Long userId, Long storeId, Long reservationId, CreateReview.Request request);

    void deleteReview(Long reviewId);


}
