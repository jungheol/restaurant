package com.zerobase.restaurant.reservation.dto;

import com.zerobase.restaurant.reservation.type.ApprovedType;
import com.zerobase.restaurant.reservation.type.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CancelReservation {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reservationId;
        private String username;
        private String storeName;
        private ReservationType reservationType;

        public static CancelReservation.Response from(ReservationDto reservationDto) {
            return CancelReservation.Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .username(reservationDto.getUsername())
                    .storeName(reservationDto.getStoreName())
                    .reservationType(reservationDto.getReservationType())
                    .build();
        }
    }
}
