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

    /**
     * 리뷰 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 리뷰 작성 내용
     */
    @Column(length = 200)
    private String reviewContent;

    /**
     * 평점
     */
    @Column(precision = 2, scale = 1)
    @Digits(integer = 1, fraction = 1)
    private double rating;

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
     * 예약 아이디
     */
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
