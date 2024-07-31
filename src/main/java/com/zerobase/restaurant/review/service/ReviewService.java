package com.zerobase.restaurant.review.service;

import com.zerobase.restaurant.review.dto.CreateReview;
import com.zerobase.restaurant.review.dto.ReviewDto;
import com.zerobase.restaurant.review.dto.UpdateReview;

public interface ReviewService {

    // 리뷰 작성
    ReviewDto createReview(Long userId, Long storeId, Long reservationId, CreateReview.Request request);

    // 리뷰 삭제
    void deleteReview(Long reviewId);

    // 리뷰 수정
    ReviewDto updateReview(Long reviewId, UpdateReview.Request request);
}
