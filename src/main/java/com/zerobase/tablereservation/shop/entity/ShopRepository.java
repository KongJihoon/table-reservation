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


    /**
     * 매니저가 등록한 매장의 정보
     * @param managerId: 매니저 아이디
     * @return 매장 리스트
     */
    @Query(" select s from Shop s " +
        "where s.manager.id = :managerId")
    List<Shop> findShopByManagerId(
            @Param("managerId") Long managerId);


}
