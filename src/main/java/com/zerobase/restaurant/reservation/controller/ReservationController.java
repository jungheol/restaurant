package com.zerobase.restaurant.reservation.controller;

import com.zerobase.restaurant.reservation.dto.CreateReservation;
import com.zerobase.restaurant.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return CreateReservation.Response.from(this.reservationService.createReservation(request));
    }
}
