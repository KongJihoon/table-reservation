package com.zerobase.tablereservation.reservation.service;

import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.ReservationDto;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;
import com.zerobase.tablereservation.reservation.dto.UpdateReservation;
import com.zerobase.tablereservation.reservation.entity.Reservation;

import java.util.List;

public interface ReservationService {

    ReservationDto createReservation(CreateReservation.Request request);

    ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request);

    List<Reservation> searchReservation(Long id);

    ReservationDto updateArrive(Long reservationId, UpdateArrive.Request request);

    ReservationDto cancelReservation(Long reservationId);


}
