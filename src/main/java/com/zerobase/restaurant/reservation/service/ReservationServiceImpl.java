package com.zerobase.restaurant.reservation.service;

import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.customer.repository.CustomerRepository;
import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.reservation.dto.ArrivalCustomer;
import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.ReservationDto;
import com.zerobase.restaurant.reservation.dto.UpdateApprove;
import com.zerobase.restaurant.reservation.repository.ReservationRepository;
import com.zerobase.restaurant.reservation.type.ApprovedType;
import com.zerobase.restaurant.store.domain.Store;
import com.zerobase.restaurant.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.zerobase.restaurant.common.type.ErrorCode.*;
import static com.zerobase.restaurant.reservation.type.ApprovedType.APPROVED;
import static com.zerobase.restaurant.reservation.type.ApprovedType.PENDING_FOR_APPROVE;
import static com.zerobase.restaurant.reservation.type.ReservationType.*;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public ReservationDto createReservation(CreateReservation.Request request) {
        Store store = this.storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        Customer customer = this.customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));

        LocalDateTime reserveTime = LocalDateTime.of(
                request.getReservationDate(), request.getReservationTime());

        boolean exists = this.reservationRepository.existsReservationTime(reserveTime);

        if (exists) {
            throw new CustomException(ALREADY_RESERVED_TIME);
        }

        Reservation reservation = this.reservationRepository.save(Reservation.builder()
                .customer(customer)
                .store(store)
                .reservationType(PENDING_FOR_RESERVATION)
                .approvedType(PENDING_FOR_APPROVE)
                .reservationDate(request.getReservationDate())
                .reservationTime(request.getReservationTime())
                .build());

        return ReservationDto.fromEntity(reservation);
    }

    @Override
    @Transactional
    public ReservationDto updateApprove(Long reservationId, UpdateApprove.Request request) {
        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        ApprovedType type = reservation.getApprovedType();
        if (type.equals(request.getApprovedType())) {
            throw new CustomException(RESERVATION_TYPE_ERROR);
        }

        reservation.setReservationType(request.getReservationType());
        reservation.setApprovedType(request.getApprovedType());

        return ReservationDto.fromEntity(
                this.reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationDto arrivalCustomer(Long reservationId, ArrivalCustomer.Request request) {
        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validationReservation(reservation, request.getArrivalTime().toLocalTime());

        reservation.setReservationType(CHECK_IN);

        return ReservationDto.fromEntity(
                this.reservationRepository.save(reservation));
    }

    private void validationReservation(Reservation reservation, LocalTime arrivalTime) {
        if (!reservation.getApprovedType().equals(APPROVED)) {
            throw new CustomException(RESERVATION_TYPE_ERROR);
        } else if (arrivalTime.isBefore(reservation.getReservationTime().minusMinutes(10L))) {
            throw new CustomException(TOO_EARLY_ARRIVAL_RESERVATION_TIME);
        } else if (arrivalTime.isAfter(reservation.getReservationTime())) {
            throw new CustomException(TOO_LATE_ARRIVAL_RESERVATION_TIME);
        }
    }

    @Override
    @Transactional
    public ReservationDto cancelReservation(Long reservationId) {
        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        reservation.setReservationType(CANCELED);

        return ReservationDto.fromEntity(
                this.reservationRepository.save(reservation));
    }

    @Override
    public List<Reservation> findReservations(Long storeId) {
        List<Reservation> reservations =
                this. reservationRepository.findReservationsByStoreId(storeId);

        if (reservations.isEmpty()) {
            throw new CustomException(RESERVATION_NOT_FOUND);
        }

        return reservations;
    }
}
