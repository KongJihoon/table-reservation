package com.zerobase.tablereservation.review.entity;

import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.global.entity.BaseEntity;
import com.zerobase.tablereservation.reservation.entity.Reservation;
import com.zerobase.tablereservation.shop.entity.Shop;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 200)
    private String reviewContent;

    @Column(precision = 2, scale = 1)
    @Digits(integer = 1, fraction = 1)
    private double rating;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
