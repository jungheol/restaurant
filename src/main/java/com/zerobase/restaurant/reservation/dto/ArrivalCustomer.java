package com.zerobase.restaurant.reservation.dto;

import com.zerobase.restaurant.reservation.type.ApprovedType;
import com.zerobase.restaurant.reservation.type.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ArrivalCustomer {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String username;
        private String phoneNumber;
        private LocalDateTime arrivalTime;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reservationId;
        private String username;
        private String storeName;
        private ReservationType reservationType;
        private ApprovedType approvedType;

        public static Response from(ReservationDto reservationDto) {
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .username(reservationDto.getUsername())
                    .storeName(reservationDto.getStoreName())
                    .reservationType(reservationDto.getReservationType())
                    .approvedType(reservationDto.getApprovedType())
                    .build();
        }
    }
}
