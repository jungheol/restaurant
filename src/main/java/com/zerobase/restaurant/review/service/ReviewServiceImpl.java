package com.zerobase.restaurant.review.service;

import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.common.type.ErrorCode;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.customer.repository.CustomerRepository;
import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.reservation.repository.ReservationRepository;
import com.zerobase.restaurant.reservation.type.ReservationType;
import com.zerobase.restaurant.review.domain.Review;
import com.zerobase.restaurant.review.dto.CreateReview;
import com.zerobase.restaurant.review.dto.ReviewDto;
import com.zerobase.restaurant.review.dto.UpdateReview;
import com.zerobase.restaurant.review.repository.ReviewRepository;
import com.zerobase.restaurant.store.domain.Store;
import com.zerobase.restaurant.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.restaurant.common.type.ErrorCode.*;
import static com.zerobase.restaurant.common.type.ErrorCode.REVIEW_ALREADY_EXIST;
import static com.zerobase.restaurant.common.type.ErrorCode.REVIEW_NOT_AVAILABLE;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;

    // 리뷰 작성
    @Override
    @Transactional
    public ReviewDto createReview(Long userId, Long storeId, Long reservationId, CreateReview.Request request) {
        Customer customer = this.customerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));

        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validationCreateReview(customer, reservation, request);

        Review review = this.reviewRepository.save(Review.builder()
                .reviewContent(request.getReviewContent())
                .rating(request.getRating())
                .customer(customer)
                .store(store)
                .reservation(reservation)
                .build());

        return ReviewDto.fromEntity(review);
    }

    // 리뷰 삭제
    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        this.reviewRepository.delete(this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND)));
    }

    // 리뷰 수정
    @Override
    @Transactional
    public ReviewDto updateReview(Long reviewId, UpdateReview.Request request) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));

        validationUpdateReview(request);

        review.setReviewContent(request.getReviewContent());
        review.setRating(request.getRating());

        return ReviewDto.fromEntity(
                this.reviewRepository.save(review));
    }

    /**
     * 리뷰 작성 관련 유효성 검사
     * 1. 점수가 5점 초과이거나 0점 미만인 경우
     * 2. 텍스트의 길이가 250자 이상인 경우
     * 3. 예약한 유저와 리뷰를 쓰려는 유저가 다른 경우
     * 4. 해당 예약에 대한 리뷰가 존재하는 경우
     * 5. 해당 리뷰를 쓸 수 있는 상태인지 확인(식당 사용 완료 확인)
     */
    private void validationCreateReview(Customer customer, Reservation reservation, CreateReview.Request request) {
        if (request.getRating() > 5 || request.getRating() < 0) {
            throw new CustomException(REVIEW_RATING_OUT_OF_RANGE);
        }
        if (request.getReviewContent().length() > 250) {
            throw new CustomException(REVIEW_TOO_LONG);
        }
        if (!reservation.getCustomer().getId().equals(customer.getId())) {
            throw new CustomException(REVIEW_USER_NOT_MATCHED);
        }
        if (this.reviewRepository.existsByReservationId(reservation.getId())) {
            throw new CustomException(REVIEW_ALREADY_EXIST);
        }
        if (!reservation.getReservationType().equals(ReservationType.CHECK_IN)) {
            throw new CustomException(REVIEW_NOT_AVAILABLE);
        }
    }

    /**
     * 리뷰 수정 관련 유효성 검사
     * 1. 점수가 5점 초과이거나 0점 미만인 경우
     * 2. 텍스트의 길이가 250자 이상인 경우
     */
    private void validationUpdateReview(UpdateReview.Request request) {
        if (request.getRating() > 5 || request.getRating() < 0) {
            throw new CustomException(REVIEW_RATING_OUT_OF_RANGE);
        }
        if (request.getReviewContent().length() > 250) {
            throw new CustomException(REVIEW_TOO_LONG);
        }
    }
}
