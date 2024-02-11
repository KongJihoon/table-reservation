package com.zerobase.tablereservation.shop.service;

import com.zerobase.tablereservation.shop.dto.CreateShop;
import com.zerobase.tablereservation.shop.dto.ShopDto;
import com.zerobase.tablereservation.shop.dto.UpdateShop;

import java.util.List;

public interface ShopService {

    /**
     * 매장 생성
     * @param request : 매장 정보 및 매니저 정보
     * @return : 매장 정보
     */
    ShopDto createShop(CreateShop.Request request);

    /**
     * 매장 정보 변경
     * @param id : 매장 아이디
     * @param request : 매장 정보
     * @return : 변경된 매장 정보
     */
    ShopDto updateShop(Long id, UpdateShop.Request request);

    /**
     * 매장 삭제
     * @param managerId : 매니저 아이디
     * @param shopId : 매장 아이디
     */
    void deleteShop(Long managerId, Long shopId);

    /**
     * 매장 세부 정보
     * @param name : 매장 이름
     * @return : 매장 세부 정보
     */
    ShopDto detailShop(String name);

    /**
     * 매장 리스트
     * @param id : 메니저 아이디
     * @return : 매장 리스트
     */
    List<ShopDto> searchShopList(Long id);

}
