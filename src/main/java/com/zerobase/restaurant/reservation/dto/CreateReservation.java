package com.zerobase.restaurant.reservation.dto;

import com.zerobase.restaurant.reservation.type.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservation {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long userId;
        private Long storeId;
        private LocalDate reservationDate;
        private LocalTime reservationTime;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String username;
        private String userPhoneNumber;
        private String storeName;
        private ReservationType reservationType;
        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response from(ReservationDto reservationDto) {
            return Response.builder()
                    .username(reservationDto.getUsername())
                    .userPhoneNumber(reservationDto.getUserPhoneNumber())
                    .storeName(reservationDto.getStoreName())
                    .reservationType(reservationDto.getReservationType())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();
        }
    }
}
