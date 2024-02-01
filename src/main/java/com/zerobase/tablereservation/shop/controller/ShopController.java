package com.zerobase.tablereservation.shop.controller;

import com.zerobase.tablereservation.shop.dto.CreateShop;
import com.zerobase.tablereservation.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/partner/create")
    @PreAuthorize("hasRole('PARTNER')")
    public CreateShop.Response createShop(
            @RequestBody CreateShop.Request request
    ){
        return CreateShop.Response.from(this.shopService.createShop(request));
    }


}
