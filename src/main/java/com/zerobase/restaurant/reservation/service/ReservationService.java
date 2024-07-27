package com.zerobase.restaurant.reservation.service;

import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.ReservationDto;
import com.zerobase.restaurant.reservation.dto.UpdateReservation;

public interface ReservationService {

    ReservationDto createReservation(CreateReservation.Request request);

    ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request);
}
