package com.zerobase.tablereservation.reservation.dto;

import com.zerobase.tablereservation.reservation.entity.Reservation;
import com.zerobase.tablereservation.reservation.type.ArrivalStatus;
import com.zerobase.tablereservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReservationDto {

    private Long reservationId;

    private String userName;

    private String userPhone;

    private String shopName;

    private ReservationStatus reservationStatus;

    private ArrivalStatus arrivalStatus;

    private LocalDate reservationDate;

    private LocalTime reservationTime;

    public static ReservationDto fromEntity(Reservation reservation){

        return ReservationDto.builder()
                .reservationId(reservation.getId())
                .userName(reservation.getCustomer().getName())
                .userPhone(reservation.getCustomer().getPhone())
                .shopName(reservation.getShop().getShopName())
                .reservationStatus(reservation.getReservationStatus())
                .arrivalStatus(reservation.getArrivalStatus())
                .reservationDate(reservation.getReservationDate())
                .reservationTime(reservation.getReservationTime())
                .build();
    }
}
