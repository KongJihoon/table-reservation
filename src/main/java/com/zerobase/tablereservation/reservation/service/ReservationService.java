package com.zerobase.tablereservation.reservation.service;

import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.ReservationDto;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;

public interface ReservationService {

    ReservationDto createReservation(CreateReservation.Request request);

    ReservationDto updateReservation(Long reservationId, UpdateArrive.Request request);

}
