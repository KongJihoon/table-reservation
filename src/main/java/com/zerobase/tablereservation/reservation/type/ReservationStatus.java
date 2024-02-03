package com.zerobase.tablereservation.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {

    APPROVAL("승인 상태"),
    USE_COMPLETED("사용 완료");
    private final String description;

}
