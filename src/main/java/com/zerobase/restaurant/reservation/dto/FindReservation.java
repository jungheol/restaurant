package com.zerobase.restaurant.reservation.dto;

import com.zerobase.restaurant.reservation.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindReservation {
    private List<ReservationDto> reservationDtoList;

    public static FindReservation from(List<Reservation> reservationList) {
        return new FindReservation(reservationList.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList()));
    }
}
