package com.zerobase.restaurant.review.domain;

import com.zerobase.restaurant.common.domain.BaseTimeEntity;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String reviewContent;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
