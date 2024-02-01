package com.zerobase.tablereservation.shop.service;


import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.global.type.ErrorCode;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.manager.entity.ManagerRepository;
import com.zerobase.tablereservation.shop.dto.CreateShop;
import com.zerobase.tablereservation.shop.dto.ShopDto;
import com.zerobase.tablereservation.shop.entity.Shop;
import com.zerobase.tablereservation.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.tablereservation.global.type.ErrorCode.ALREADY_EXIST_SHOP;
import static com.zerobase.tablereservation.global.type.ErrorCode.MANAGER_NOT_FOUND;

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
}
