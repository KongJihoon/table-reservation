package com.zerobase.tablereservation.shop.service;

import com.zerobase.tablereservation.shop.dto.CreateShop;
import com.zerobase.tablereservation.shop.dto.ShopDto;
import com.zerobase.tablereservation.shop.dto.UpdateShop;

public interface ShopService {

    ShopDto createShop(CreateShop.Request request);

    ShopDto updateShop(Long id, UpdateShop.Request request);

    void deleteShop(Long managerId, Long shopId);

}
