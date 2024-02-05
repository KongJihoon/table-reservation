package com.zerobase.tablereservation.reservation.controller;

import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.SearchReservation;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;
import com.zerobase.tablereservation.reservation.dto.UpdateReservation;
import com.zerobase.tablereservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/partner/approval/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateReservation.Response updateReservation(
            @PathVariable Long id,
            @RequestBody UpdateReservation.Request request
    ){
        return UpdateReservation.Response.from(
                reservationService.updateReservation(id, request)
        );
    }

    @GetMapping("/partner/reservation-list/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public SearchReservation getReservation(
            @PathVariable Long id
    ){
        return SearchReservation.from(
                reservationService.searchReservation(id)
        );
    }


    @PutMapping("/kiosk/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateArrive.Response updateArrive(
            @PathVariable Long id,
            @RequestBody UpdateArrive.Request request
    ){

        return UpdateArrive.Response.from(
                reservationService.updateArrive(id, request)
        );
    }

    @DeleteMapping("/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> cancelReservation(
            @RequestParam(name = "reservationId") Long reservationId
    ){
        return ResponseEntity.ok(
                reservationService.cancelReservation(reservationId)
        );
    }
}
