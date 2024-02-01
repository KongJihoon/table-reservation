package com.zerobase.tablereservation.shop.service;

import com.zerobase.tablereservation.shop.dto.CreateShop;
import com.zerobase.tablereservation.shop.dto.ShopDto;

public interface ShopService {

    ShopDto createShop(CreateShop.Request request);

}
