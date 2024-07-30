package com.zerobase.restaurant.review.repository;

import com.zerobase.restaurant.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
