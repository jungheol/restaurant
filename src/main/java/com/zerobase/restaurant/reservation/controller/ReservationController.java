package com.zerobase.restaurant.reservation.controller;

import com.zerobase.restaurant.reservation.dto.*;
import com.zerobase.restaurant.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 예약 생성
     * @param request 예약 생성 요청
     *                - userId : 예약 유저 id
     *                - storeId : 예약 식당 id
     *                - reservationDate : 예약 일자
     *                - reservationTime : 예약 시간
     * @return 예약 생성 결과
     *                - username : 예약자 이름
     *                - userPhoneNumber : 예약자 핸드폰 번호
     *                - storeName : 예약 식당 이름
     *                - reservationType : 예약 상황
     *                - reservationDate : 예약 일자
     *                - reservationTime : 예약 시간
     */
    @PostMapping("/customer/create")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request
    ) {
        return CreateReservation.Response.from(
                this.reservationService.createReservation(request));
    }

    /**
     * 예약 승인/거절
     * @param request 예약 승인/거절 요청
     *                - reservationType : 예약 상황
     *                - approvedType : 승인 여부
     * @return 예약 승인/거절 결과
     *                - reservationId : 예약 id
     *                - username : 예약자 이름
     *                - storeName : 예약 식당 이름
     *                - reservationType : 예약 상황
     *                - approvedType : 승인 여부
     *                - reservationDate : 예약 일자
     *                - reservationTime : 예약 시간
     */
    @PutMapping("/partner/approve/{reservationId}")
    public UpdateApprove.Response updateReservation(
            @PathVariable("reservationId") Long reservationId,
            @RequestBody UpdateApprove.Request request
    ) {
        return UpdateApprove.Response.from(
                this.reservationService.updateApprove(reservationId, request));
    }

    /**
     * 방문자 도착
     * @param request 방문자 도착 요청
     *                - username : 예약자 이름
     *                - phoneNumber : 예약자 핸드폰 번호
     *                - arrivalTime : 예약자 도착 시간
     * @return 방문자 도착 결과
     *                - reservationId : 예약 id
     *                - username : 예약자 이름
     *                - storeName : 예약 식당 이름
     *                - reservationType : 예약 상황
     *                - approvedType : 승인 여부
     */
    @PutMapping("/customer/arrival/{reservationId}")
    public ArrivalCustomer.Response updateArrivalCustomer(
            @PathVariable("reservationId") Long reservationId,
            @RequestBody ArrivalCustomer.Request request
    ) {
        return ArrivalCustomer.Response.from(
                this.reservationService.arrivalCustomer(reservationId, request));
    }

    /**
     * 예약 취소
     * @param reservationId : 예약 id
     * @return 예약 취소 결과
     *                - reservationId : 예약 id
     *                - username : 예약자 이름
     *                - storeName : 예약 식당 이름
     *                - reservationType : 예약 상황
     */
    @PutMapping("/customer/cancel")
    public CancelReservation.Response cancelReservation(
            @RequestParam(name = "reservationId") Long reservationId
    ) {
        return CancelReservation.Response.from(
                this.reservationService.cancelReservation(reservationId));
    }

    /**
     * 예약 리스트 조회
     * storeId 에 해당하는 식당에 대한 예약 리스트 조회
     * @return 예약 리스트 조회 결과
     *                - reservationId : 예약 id
     *                - username : 예약자 이름
     *                - userPhoneNumber : 예약자 핸드폰 번호
     *                - storeName : 예약 식당 이름
     *                - reservationType : 예약 상황
     *                - approvedType : 승인 상황
     *                - reservationDate : 예약 일자
     *                - reservationTime : 예약 시간
     */
    @GetMapping("/partner/reservation-list/{storeId}")
    public FindReservation getReservationList(@PathVariable("storeId") Long storeId) {
        return FindReservation.from(
                this.reservationService.findReservations(storeId));
    }
}
