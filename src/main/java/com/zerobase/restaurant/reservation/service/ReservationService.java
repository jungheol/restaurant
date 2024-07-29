package com.zerobase.restaurant.reservation.service;

import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.reservation.dto.ArrivalCustomer;
import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.ReservationDto;
import com.zerobase.restaurant.reservation.dto.UpdateApprove;

import java.util.List;

public interface ReservationService {

    ReservationDto createReservation(CreateReservation.Request request);

    ReservationDto updateApprove(Long reservationId, UpdateApprove.Request request);

    ReservationDto arrivalCustomer(Long reservationId, ArrivalCustomer.Request request);

    ReservationDto cancelReservation(Long reservationId);

    List<Reservation> findReservations(Long storeId);
}
