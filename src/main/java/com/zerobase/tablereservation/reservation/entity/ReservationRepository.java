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


    @Query("SELECT COUNT(r) > 0 " +
            "FROM Reservation r " +
            "WHERE r.reservationDate = :#{#reservationTime.toLocalDate()} " +
            "AND r.reservationTime = :#{#reservationTime.toLocalTime()}")
    boolean existReservationTime(@Param("reservationTime")LocalDateTime reservationTime);

    @Query(" SELECT r FROM Reservation r " +
            " WHERE r.shop.manager.id = :id " +
            " ORDER BY r.reservationDate " )
    List<Reservation> findAllByManagerReservation(@Param("id") Long id);


}
