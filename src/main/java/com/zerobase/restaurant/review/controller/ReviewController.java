package com.zerobase.restaurant.review.controller;

import com.zerobase.restaurant.review.dto.CreateReview;
import com.zerobase.restaurant.review.dto.UpdateReview;
import com.zerobase.restaurant.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     * @param userId : 예약 유저 id
     *        storeId : 예약 식당 id
     *        reservationId : 예약 id
     *        request
     *                - reviewContent : 리뷰 내용
     *                - rating : 점수 (1-5점 사이)
     * @return 리뷰 작성 결과
     *                - reviewId : 리뷰 id
     *                - reviewContent : 리뷰 내용
     *                - rating : 점수 (1-5점 사이)
     *                - username : 유저 이름
     *                - storeName : 식당 이름
     */
    @PostMapping("/customer/create")
    public CreateReview.Response createReview(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "storeId") Long storeId,
            @RequestParam(name = "reservationId") Long reservationId,
            @RequestBody CreateReview.Request request
    ) {
        return CreateReview.Response.from(
                this.reviewService.createReview(userId, storeId, reservationId, request));
    }

    /**
     * 리뷰 삭제
     * @return 리뷰 삭제 결과
     *                - "해당 리뷰 삭제를 완료했습니다."
     */
    @DeleteMapping("/customer/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {
        this.reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("해당 리뷰 삭제를 완료했습니다.");
    }

    /**
     * 리뷰 수정
     * @param request 리뷰 수정 요청
     *                - reviewContent : 리뷰 내용
     *                - rating : 점수 (1-5점 사이)
     * @return 리뷰 수정 결과
     *                - reviewId : 리뷰 id
     *                - reviewContent : 리뷰 내용
     *                - rating : 점수 (1-5점 사이)
     */
    @PutMapping("/update/{reviewId}")
    public UpdateReview.Response updateReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody UpdateReview.Request request
    ) {
        return UpdateReview.Response.from(
                this.reviewService.updateReview(reviewId, request));
    }
}
