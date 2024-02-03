package com.zerobase.tablereservation.reservation.dto;


import com.zerobase.tablereservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservation {


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        @NotNull
        private Long userId;

        @NotNull
        private Long shopId;

        private LocalDate reservationDate;
        private LocalTime reservationTime;



    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{

        private String userName;
        private String userPhone;
        private String shopName;
        private ReservationStatus reservationStatus;

        private LocalDate reservationDate;
        private LocalTime reservationTime;


        public static Response from(ReservationDto reservationDto){

            return Response.builder()
                    .userName(reservationDto.getUserName())
                    .userPhone(reservationDto.getUserPhone())
                    .shopName(reservationDto.getShopName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();
        }
    }

}
