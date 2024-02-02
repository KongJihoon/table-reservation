package com.zerobase.tablereservation.shop.service;


import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.global.type.ErrorCode;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.manager.entity.ManagerRepository;
import com.zerobase.tablereservation.shop.dto.CreateShop;
import com.zerobase.tablereservation.shop.dto.ShopDto;
import com.zerobase.tablereservation.shop.dto.UpdateShop;
import com.zerobase.tablereservation.shop.entity.Shop;
import com.zerobase.tablereservation.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.zerobase.tablereservation.global.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;
    private final ManagerRepository managerRepository;

    /**
     * 매장 생성
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ShopDto createShop(CreateShop.Request request) {
        log.info("매장 생성");

        Manager manager = this.managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));

        if(this.shopRepository.existsByShopName(request.getShopName())){
            throw new CustomException(ALREADY_EXIST_SHOP);
        }
        log.info("매장 생성 완료");

        return ShopDto.fromEntity(this.shopRepository.save(Shop.builder()
                .manager(manager)
                .shopName(request.getShopName())
                .location(request.getLocation())
                .phone(request.getPhone())
                .build()));
    }


    /**
     * 매장 수정
     * @param id
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ShopDto updateShop(Long id, UpdateShop.Request request) {
        log.info("매장 정보 변경");

        Shop shop = this.shopRepository.findById(id)
                .orElseThrow(() -> new CustomException(SHOP_NOT_FOUND));

        if(!shop.getManager().getId().equals(request.getManagerId())){
            throw new CustomException(SHOP_NOT_MATCH_MANAGER);
        }

        shop.setShopName(request.getShopName());
        shop.setLocation(request.getLocation());

        log.info("매장 정보 변경 완료");

        return ShopDto.fromEntity(this.shopRepository.save(shop));
    }

    /**
     * 매장 삭제
     * @param managerId
     * @param shopId
     */
    @Override
    @Transactional
    public void deleteShop(Long managerId, Long shopId) {
        log.info("매장 정보 삭제");

        Shop shop = this.shopRepository.findById(shopId)
                .orElseThrow(() -> new CustomException(SHOP_NOT_FOUND));

        if(!shop.getManager().getId().equals(managerId)){
            throw new CustomException(SHOP_NOT_MATCH_MANAGER);
        }

        this.shopRepository.delete(shop);

    }


    /**
     * 매장 검색
     * @param id
     * @return
     */
    @Override
    public List<ShopDto> searchShopList(Long id) {
        log.info("매장 리스트 확인");

        List<Shop> shopList = shopRepository.findShopByManagerId(id);

        if(shopList.isEmpty()){
            throw new CustomException(SHOP_NOT_FOUND);
        }

        log.info("매장 리스트 확인 완료");
        return shopList.stream()
                .map(ShopDto::fromEntity).collect(Collectors.toList());

    }
}
