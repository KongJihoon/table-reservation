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

import static com.zerobase.tablereservation.global.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;
    private final ManagerRepository managerRepository;

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
}
