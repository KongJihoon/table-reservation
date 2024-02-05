package com.zerobase.tablereservation.reservation.service;

import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.customer.entity.CustomerRepository;
import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.ReservationDto;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;
import com.zerobase.tablereservation.reservation.dto.UpdateReservation;
import com.zerobase.tablereservation.reservation.entity.Reservation;
import com.zerobase.tablereservation.reservation.entity.ReservationRepository;
import com.zerobase.tablereservation.reservation.type.ReservationStatus;
import com.zerobase.tablereservation.shop.entity.Shop;
import com.zerobase.tablereservation.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.zerobase.tablereservation.global.type.ErrorCode.*;
import static com.zerobase.tablereservation.reservation.type.ArrivalStatus.ARRIVED;
import static com.zerobase.tablereservation.reservation.type.ArrivalStatus.READY;
import static com.zerobase.tablereservation.reservation.type.ReservationStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final ShopRepository shopRepository;
    private final CustomerRepository customerRepository;


    @Override
    @Transactional
    public ReservationDto createReservation(CreateReservation.Request request) {
        log.info("예약 등록: {}", request.toString());

        Shop shop = shopRepository.findById(request.getShopId())
                .orElseThrow(() -> new CustomException(SHOP_NOT_FOUND));

        Customer customer = customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));

        LocalDateTime reserveTime = LocalDateTime.of(
                request.getReservationDate(), request.getReservationTime()
        );

        boolean exist = reservationRepository.existReservationTime(reserveTime);

        if(exist){
            throw new CustomException(ALREADY_RESERVE);
        }
        Reservation reservation = reservationRepository.save(Reservation.builder()
                .customer(customer)
                .shop(shop)
                .reservationStatus(STANDBY)
                .arrivalStatus(READY)
                .reservationDate(request.getReservationDate())
                .reservationTime(request.getReservationTime()).build());


        return ReservationDto.fromEntity(reservation);
    }

    @Override
    @Transactional
    public ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request) {

        log.info("예약 상태 변경");

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        ReservationStatus reservationStatus = reservation.getReservationStatus();

        if(reservationStatus.equals(request.getReservationStatus())){
            throw new CustomException(RESERVATION_STATUS_ERROR);
        }

        reservation.setReservationStatus(request.getReservationStatus());

        return ReservationDto.fromEntity(
                this.reservationRepository.save(reservation)
        );
    }

    @Override
    public List<Reservation> searchReservation(Long id) {

        log.info("예약 요청 목록 조회");

        List<Reservation> reservations =
                reservationRepository.findAllByManagerReservation(id);

        if(reservations.isEmpty()){
            throw new CustomException(RESERVATION_NOT_FOUND);
        }

        return reservations;
    }

    @Override
    @Transactional
    public ReservationDto updateArrive(Long reservationId, UpdateArrive.Request request) {
        log.info("예약자 도착 여부 변경");

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validationReservation(reservation, request.getArriveTime().toLocalTime());

        reservation.setArrivalStatus(ARRIVED);
        reservation.setReservationStatus(USE_COMPLETED);


        return ReservationDto.fromEntity(reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationDto cancelReservation(Long reservationId) {
        log.info("예약 취소");

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        reservation.setReservationStatus(CANCELED);

        return ReservationDto.fromEntity(
                reservationRepository.save(reservation)
        );
    }


    /**
     * 유효성 검사
     * @param reservation
     * @param arriveTime
     */
    private void validationReservation(Reservation reservation, LocalTime arriveTime){
        if(!reservation.getReservationStatus().equals(APPROVAL)){
            throw new CustomException(RESERVATION_STATUS_ERROR);
        } else if(arriveTime.isAfter(reservation.getReservationTime())){
            throw new CustomException(RESERVATION_TIME_EXCEEDED);
        }else if(arriveTime.isBefore(reservation.getReservationTime().minusMinutes(10L))){
            throw new CustomException(CHECK_IT_10_MINUTES_BEFORE_THE_RESERVATION_TIME);
        }
    }
}
