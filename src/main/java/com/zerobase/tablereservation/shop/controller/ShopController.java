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

    /**
     * 매장 등록
     * @param request : 매장 등록 데이터
     * @return : 매장 등록 완료
     */
    @PostMapping("/partner/create")
    @PreAuthorize("hasRole('PARTNER')")
    public CreateShop.Response createShop(
            @RequestBody CreateShop.Request request
    ){
        return CreateShop.Response.from(this.shopService.createShop(request));
    }

    /**
     * 매장 정보 수정
     * @param id : 매장 아이디
     * @param request : 매장 정보
     * @return : 매장 수정 완료 정보
     */
    @PutMapping("/partner/update/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateShop.Response updateShop(
            @PathVariable Long id,
            @RequestBody UpdateShop.Request request
    ){
        return UpdateShop.Response.from(this.shopService.updateShop(id, request));
    }

    /**
     * 매장 삭제
     * @param managerId : 매니저 아이디
     * @param shopId : 매장 아이디
     * @return : "매장 삭제 완료"
     */
    @DeleteMapping("/partner/delete")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> deleteShop(
            @RequestParam("id") Long managerId,
            @RequestParam("shop") Long shopId
    ){
        this.shopService.deleteShop(managerId,shopId);
        return ResponseEntity.ok("매장 삭제 완료");
    }


    /**
     * 매장 세부 정보 조회
     * @param name : 매장 이름
     * @return : 매장 정보
     */
    @GetMapping("/detail/{name}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> detailShop(
            @PathVariable String name
    ){
        return ResponseEntity.ok(shopService.detailShop(name));
    }

    /**
     * 매니저 매장 리스트 조회
     * @param id : 매니저 아이디
     * @return : 매장 리스트
     */
    @GetMapping("/partner/list")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> getShopList(
            @RequestParam("managerId") Long id
    ){
        return ResponseEntity.ok(shopService.searchShopList(id));
    }



}
