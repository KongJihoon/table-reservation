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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Enumerated(EnumType.STRING)
    private ArrivalStatus arrivalStatus;

    private LocalDate reservationDate;

    private LocalTime reservationTime;
}
