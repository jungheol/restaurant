package com.zerobase.restaurant.review.service;

import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.common.type.ErrorCode;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.customer.repository.CustomerRepository;
import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.reservation.repository.ReservationRepository;
import com.zerobase.restaurant.review.domain.Review;
import com.zerobase.restaurant.review.dto.CreateReview;
import com.zerobase.restaurant.review.dto.ReviewDto;
import com.zerobase.restaurant.review.repository.ReviewRepository;
import com.zerobase.restaurant.store.domain.Store;
import com.zerobase.restaurant.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

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
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND)));
    }
}
