package com.zerobase.restaurant.reservation.domain;

import com.zerobase.restaurant.common.domain.BaseTimeEntity;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.reservation.type.ApprovedType;
import com.zerobase.restaurant.reservation.type.ReservationType;
import com.zerobase.restaurant.store.domain.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    @Enumerated(EnumType.STRING)
    private ApprovedType approvedType;

    private LocalDate reservationDate;

    private LocalTime reservationTime;
}
