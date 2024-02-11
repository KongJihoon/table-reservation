package com.zerobase.tablereservation.reservation.entity;

import com.zerobase.tablereservation.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    /**
     * 매장 예약 여부 확인
     * @param reservationTime : 예약 시간
     * @return : 예약 여부
     */
    @Query("SELECT COUNT(r) > 0 " +
            "FROM Reservation r " +
            "WHERE r.reservationDate = :#{#reservationTime.toLocalDate()} " +
            "AND r.reservationTime = :#{#reservationTime.toLocalTime()}")
    boolean existReservationTime(@Param("reservationTime")LocalDateTime reservationTime);

    /**
     * 매장 예약 정보 확인
     * @param id : 매니저 아이디
     * @return : 예약 리스트
     */
    @Query(" SELECT r FROM Reservation r " +
            " WHERE r.shop.manager.id = :id " +
            " ORDER BY r.reservationDate " )
    List<Reservation> findAllByManagerReservation(@Param("id") Long id);


}
