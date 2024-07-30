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

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        this.reviewRepository.delete(this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND)));
    }

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

    private void validationUpdateReview(UpdateReview.Request request) {
        if (request.getRating() > 5 || request.getRating() < 0) {
            throw new CustomException(REVIEW_RATING_OUT_OF_RANGE);
        }
        if (request.getReviewContent().length() > 250) {
            throw new CustomException(REVIEW_TOO_LONG);
        }
    }
}
