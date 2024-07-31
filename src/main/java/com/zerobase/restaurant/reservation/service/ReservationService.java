package com.zerobase.restaurant.reservation.service;

import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.reservation.dto.ArrivalCustomer;
import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.ReservationDto;
import com.zerobase.restaurant.reservation.dto.UpdateApprove;

import java.util.List;

public interface ReservationService {

    // 예약 생성
    ReservationDto createReservation(CreateReservation.Request request);

    // 예약 승인/거절
    ReservationDto updateApprove(Long reservationId, UpdateApprove.Request request);

    // 방문자 도착
    ReservationDto arrivalCustomer(Long reservationId, ArrivalCustomer.Request request);

    // 예약 취소
    ReservationDto cancelReservation(Long reservationId);

    // 예약 리스트 조회
    List<Reservation> findReservations(Long storeId);
}
