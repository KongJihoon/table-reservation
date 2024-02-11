package com.zerobase.tablereservation.reservation.entity;


import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.global.entity.BaseEntity;
import com.zerobase.tablereservation.reservation.type.ArrivalStatus;
import com.zerobase.tablereservation.reservation.type.ReservationStatus;
import com.zerobase.tablereservation.shop.entity.Shop;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation extends BaseEntity {

    /**
     * 예약 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 고객 아이디
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * 매장 아이디
     */
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    /**
     * 예약 상태
     */
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    /**
     * 도착 여부 상태
     */
    @Enumerated(EnumType.STRING)
    private ArrivalStatus arrivalStatus;

    /**
     * 예약 날짜
     */
    private LocalDate reservationDate;

    /**
     * 예약 시간
     */
    private LocalTime reservationTime;
}
