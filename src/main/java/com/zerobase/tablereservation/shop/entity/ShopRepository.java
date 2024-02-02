package com.zerobase.tablereservation.shop.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    boolean existsByShopName(String name);
    Optional<Shop> findByShopName(String name);


    @Query(" select s from Shop s " +
        "where s.manager.id = :managerId")
    List<Shop> findShopByManagerId(
            @Param("managerId") Long managerId);


}
