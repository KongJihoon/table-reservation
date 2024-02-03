package com.zerobase.tablereservation.reservation.dto;

import com.zerobase.tablereservation.reservation.type.ArrivalStatus;
import com.zerobase.tablereservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UpdateArrive {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{

        private String userName;
        private String userPhone;
        private LocalDateTime arriveTime;

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
        private ArrivalStatus arrivalStatus;

        public static Response from(ReservationDto reservationDto){
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .userName(reservationDto.getUserName())
                    .shopName(reservationDto.getShopName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .arrivalStatus(reservationDto.getArrivalStatus())
                    .build();
        }

    }
}
