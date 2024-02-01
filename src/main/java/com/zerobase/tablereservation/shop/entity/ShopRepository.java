package com.zerobase.tablereservation.shop.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    boolean existsByShopName(String name);
    Optional<Shop> findByShopName(String name);

}
