package com.zerobase.restaurant.reservation.service;

import com.zerobase.restaurant.common.exception.CustomException;
import com.zerobase.restaurant.common.type.ErrorCode;
import com.zerobase.restaurant.customer.domain.Customer;
import com.zerobase.restaurant.customer.repository.CustomerRepository;
import com.zerobase.restaurant.reservation.domain.Reservation;
import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.ReservationDto;
import com.zerobase.restaurant.reservation.dto.UpdateReservation;
import com.zerobase.restaurant.reservation.repository.ReservationRepository;
import com.zerobase.restaurant.reservation.type.ApprovedType;
import com.zerobase.restaurant.reservation.type.ReservationType;
import com.zerobase.restaurant.store.domain.Store;
import com.zerobase.restaurant.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Customer customer = this.customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        LocalDateTime reserveTime = LocalDateTime.of(
                request.getReservationDate(), request.getReservationTime());

        boolean exists = this.reservationRepository.existsReservationTime(reserveTime);

        if (exists) {
            throw new CustomException(ErrorCode.ALREADY_RESERVED_TIME);
        }

        Reservation reservation = this.reservationRepository.save(Reservation.builder()
                .customer(customer)
                .store(store)
                .reservationType(ReservationType.PENDING)
                .approvedType(ApprovedType.PENDING)
                .reservationDate(request.getReservationDate())
                .reservationTime(request.getReservationTime())
                .build());

        return ReservationDto.fromEntity(reservation);
    }

    @Override
    @Transactional
    public ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request) {
        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        ReservationType type = reservation.getReservationType();
        if (type.equals(request.getReservationType())) {
            throw new CustomException(ErrorCode.RESERVATION_TYPE_ERROR);
        }

        reservation.setReservationType(request.getReservationType());

        return ReservationDto.fromEntity(this.reservationRepository.save(reservation));
    }
}
