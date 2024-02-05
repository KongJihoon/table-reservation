package com.zerobase.tablereservation.reservation.dto;

import com.zerobase.tablereservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateReservation {


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private ReservationStatus reservationStatus;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{

        private Long reservationId;
        private String userName;
        private String shopName;
        private ReservationStatus reservationStatus;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response from(ReservationDto reservationDto){
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .userName(reservationDto.getUserName())
                    .shopName(reservationDto.getShopName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();

        }

    }

}
