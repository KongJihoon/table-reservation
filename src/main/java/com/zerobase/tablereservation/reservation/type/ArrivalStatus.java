package com.zerobase.tablereservation.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrivalStatus {

    READY("대기 상태"),
    ARRIVED("도착 완료");

    private final String description;

}