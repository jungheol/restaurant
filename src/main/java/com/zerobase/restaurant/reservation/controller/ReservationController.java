package com.zerobase.restaurant.reservation.controller;

import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.dto.UpdateReservation;
import com.zerobase.restaurant.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request
    ) {
        return CreateReservation.Response.from(
                this.reservationService.createReservation(request));
    }

    @PutMapping("/approval/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateReservation.Response updateReservation(
            @PathVariable Long id,
            @RequestBody UpdateReservation.Request request
    ) {
        return UpdateReservation.Response.from(
                this.reservationService.updateReservation(id, request));
    }
}
