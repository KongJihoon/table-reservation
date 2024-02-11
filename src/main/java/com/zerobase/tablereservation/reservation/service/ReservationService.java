package com.zerobase.tablereservation.reservation.service;

import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.ReservationDto;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;
import com.zerobase.tablereservation.reservation.dto.UpdateReservation;
import com.zerobase.tablereservation.reservation.entity.Reservation;

import java.util.List;

public interface ReservationService {

    /**
     * 예약 등록
     * @param request : 예약 시 필요 정보
     * @return : 예약 등록 확인 정보
     */
    ReservationDto createReservation(CreateReservation.Request request);

    /**
     * 예약 승인 여부 변경
     * @param reservationId : 예약 아이디
     * @param request : 예약 승인 여부
     * @return : 변경된 예약 정보
     */
    ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request);

    /**
     * 예약 리스트 조회
     * @param id : 매니저 아이디
     * @return : 예약 리스트
     */
    List<Reservation> searchReservation(Long id);

    /**
     * 도착 여부 변경
     * @param reservationId : 예약 아이디
     * @param request : 예약 고객 정보
     * @return : 유저 도착 여부
     */
    ReservationDto updateArrive(Long reservationId, UpdateArrive.Request request);

    /**
     * 예약 취소
     * @param reservationId : 예약 아이디
     * @return : 예약 취소
     */
    ReservationDto cancelReservation(Long reservationId);


}
