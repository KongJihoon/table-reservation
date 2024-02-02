package com.zerobase.tablereservation.shop.controller;

import com.zerobase.tablereservation.shop.dto.CreateShop;
import com.zerobase.tablereservation.shop.dto.UpdateShop;
import com.zerobase.tablereservation.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/partner/update/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateShop.Response updateShop(
            @PathVariable Long id,
            @RequestBody UpdateShop.Request request
    ){
        return UpdateShop.Response.from(this.shopService.updateShop(id, request));
    }

    @DeleteMapping("/partner/delete")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> deleteShop(
            @RequestParam("id") Long managerId,
            @RequestParam("shop") Long shopId
    ){
        this.shopService.deleteShop(managerId,shopId);
        return ResponseEntity.ok("매장 삭제 완료");
    }



}
