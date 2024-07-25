package com.zerobase.restaurant.reservation.dto;

import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.reservation.type.ApprovedType;
import com.zerobase.restaurant.reservation.type.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReservationDto {
    private Long reservationId;
    private String username;
    private String userPhoneNumber;
    private String storeName;
    private ReservationType reservationType;
    private ApprovedType approvedType;
    private LocalDate reservationDate;
    private LocalTime reservationTime;

    public static ReservationDto fromEntity(Reservation reservation) {
        return ReservationDto.builder()
                .reservationId(reservation.getId())
                .username(reservation.getCustomer().getUsername())
                .userPhoneNumber(reservation.getCustomer().getPhoneNumber())
                .storeName(reservation.getStore().getStoreName())
                .reservationType(reservation.getReservationType())
                .approvedType(reservation.getApprovedType())
                .reservationDate(reservation.getReservationDate())
                .reservationTime(reservation.getReservationTime())
                .build();
    }
}
