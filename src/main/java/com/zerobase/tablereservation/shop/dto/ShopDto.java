package com.zerobase.tablereservation.shop.dto;

import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.shop.entity.Shop;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {

    private Manager manager;
    private String shopName;
    private String location;
    private String phone;

    public static ShopDto fromEntity(Shop shop){

        return ShopDto.builder()
                .manager(shop.getManager())
                .shopName(shop.getShopName())
                .location(shop.getLocation())
                .phone(shop.getPhone())
                .build();
    }

}
