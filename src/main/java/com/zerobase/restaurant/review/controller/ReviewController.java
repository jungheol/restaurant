package com.zerobase.restaurant.review.controller;

import com.zerobase.restaurant.review.dto.CreateReview;
import com.zerobase.restaurant.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public CreateReview.Response createReview(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "storeId") Long storeId,
            @RequestParam(name = "reservationId") Long reservationId,
            @RequestBody CreateReview.Request request
    ) {
        return CreateReview.Response.from(
                this.reviewService.createReview(userId, storeId, reservationId, request));
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        this.reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("해당 리뷰 삭제를 완료했습니다.");
    }
}
