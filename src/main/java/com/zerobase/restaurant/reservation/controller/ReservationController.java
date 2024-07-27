package com.zerobase.restaurant.reservation.controller;

import com.zerobase.restaurant.reservation.dto.ArrivalCustomer;
import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.UpdateApprove;
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

    @PostMapping("/customer/create")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request
    ) {
        return CreateReservation.Response.from(
                this.reservationService.createReservation(request));
    }

    @PutMapping("/partner/approve/{id}")
    public UpdateApprove.Response updateReservation(
            @PathVariable Long id,
            @RequestBody UpdateApprove.Request request
    ) {
        return UpdateApprove.Response.from(
                this.reservationService.updateApprove(id, request));
    }

    @PutMapping("/customer/arrival/{id}")
    public ArrivalCustomer.Response updateArrivalCustomer(
            @PathVariable Long id,
            @RequestBody ArrivalCustomer.Request request
    ) {
        return ArrivalCustomer.Response.from(
                this.reservationService.arrivalCustomer(id, request));
    }

    @PutMapping("/customer/cancel")
    public ResponseEntity<?> cancelReservation(
            @RequestParam(name = "reservationId") Long reservationId
    ) {
        return ResponseEntity.ok(
                this.reservationService.cancelReservation(reservationId));
    }
}
