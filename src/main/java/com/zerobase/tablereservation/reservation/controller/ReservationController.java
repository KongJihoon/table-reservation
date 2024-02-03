package com.zerobase.tablereservation.reservation.controller;

import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;
import com.zerobase.tablereservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request
    ){
        return CreateReservation.Response.from(
                reservationService.createReservation(request)
        );
    }

    @PutMapping("/kiosk/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateArrive.Response updateArrive(
            @PathVariable Long id,
            @RequestBody UpdateArrive.Request request
    ){

        return UpdateArrive.Response.from(
                reservationService.updateReservation(id, request)
        );
    }

}
