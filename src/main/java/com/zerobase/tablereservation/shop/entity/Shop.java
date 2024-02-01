package com.zerobase.tablereservation.shop.entity;


import com.zerobase.tablereservation.global.entity.BaseEntity;
import com.zerobase.tablereservation.manager.entity.Manager;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private String shopName;

    private String location;

    private String phone;

}
