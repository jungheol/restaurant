package com.zerobase.restaurant.reservation.dto;

import com.zerobase.restaurant.reservation.type.ApprovedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateApprove {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private ApprovedType approvedType;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reservationId;
        private String username;
        private String storeName;
        private ApprovedType approvedType;
        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response from(ReservationDto reservationDto) {
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .username(reservationDto.getUsername())
                    .storeName(reservationDto.getStoreName())
                    .approvedType(reservationDto.getApprovedType())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();
        }
    }
}
