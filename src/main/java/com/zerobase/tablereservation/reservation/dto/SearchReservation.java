package com.zerobase.tablereservation.reservation.dto;


import com.zerobase.tablereservation.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchReservation {

    private List<ReservationDto> reservationList;

    public static SearchReservation from(List<Reservation> reservations){
        return new SearchReservation(reservations.stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList()));
    }


}
