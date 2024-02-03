package com.zerobase.tablereservation.reservation.service;

import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.customer.entity.CustomerRepository;
import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.ReservationDto;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;
import com.zerobase.tablereservation.reservation.entity.Reservation;
import com.zerobase.tablereservation.reservation.entity.ReservationRepository;
import com.zerobase.tablereservation.shop.entity.Shop;
import com.zerobase.tablereservation.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.zerobase.tablereservation.global.type.ErrorCode.*;
import static com.zerobase.tablereservation.reservation.type.ArrivalStatus.ARRIVED;
import static com.zerobase.tablereservation.reservation.type.ArrivalStatus.READY;
import static com.zerobase.tablereservation.reservation.type.ReservationStatus.APPROVAL;
import static com.zerobase.tablereservation.reservation.type.ReservationStatus.USE_COMPLETED;

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
                .reservationStatus(APPROVAL)
                .arrivalStatus(READY)
                .reservationDate(request.getReservationDate())
                .reservationTime(request.getReservationTime()).build());


        return ReservationDto.fromEntity(reservation);
    }

    @Override
    @Transactional
    public ReservationDto updateReservation(Long reservationId, UpdateArrive.Request request) {
        log.info("예약자 도착 여부 변경");

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validationReservation(reservation, request.getArriveTime().toLocalTime());

        reservation.setArrivalStatus(ARRIVED);
        reservation.setReservationStatus(USE_COMPLETED);


        return ReservationDto.fromEntity(reservationRepository.save(reservation));
    }


    private void validationReservation(Reservation reservation, LocalTime arriveTime){
        if(arriveTime.isAfter(reservation.getReservationTime())){
            throw new CustomException(RESERVATION_TIME_EXCEEDED);
        }else if(arriveTime.isBefore(reservation.getReservationTime().minusMinutes(10L))){
            throw new CustomException(CHECK_IT_10_MINUTES_BEFORE_THE_RESERVATION_TIME);
        }
    }
}
