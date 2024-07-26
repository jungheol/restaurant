package com.zerobase.restaurant.reservation.service;

import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.ReservationDto;

public interface ReservationService {

    ReservationDto createReservation(CreateReservation.Request request);

}
