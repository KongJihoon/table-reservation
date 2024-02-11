package com.zerobase.tablereservation.shop.entity;


import com.zerobase.tablereservation.global.entity.BaseEntity;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.reservation.entity.Reservation;
import com.zerobase.tablereservation.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends BaseEntity {
    /**
     * 매장 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;


    /**
     * 매니저 아이디
     */
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    /**
     * 매장 이름
     */
    private String shopName;

    /**
     * 매장 위치
     */
    private String location;

    /**
     * 매장 전화번호
     */
    private String phone;

    /**
     * reservation 조인
     */
    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservationList = new ArrayList<>();

    /**
     * review 조인
     */
    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

}
