package com.zerobase.tablereservation.reservation.controller;

import com.zerobase.tablereservation.reservation.dto.CreateReservation;
import com.zerobase.tablereservation.reservation.dto.SearchReservation;
import com.zerobase.tablereservation.reservation.dto.UpdateArrive;
import com.zerobase.tablereservation.reservation.dto.UpdateReservation;
import com.zerobase.tablereservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 매장 예약 생성
     * @param request : 예약 시 필요 정보
     * @return : 예약 정보
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request
    ){
        return CreateReservation.Response.from(
                reservationService.createReservation(request)
        );
    }

    /**
     * 예약 승인 및 취소(매니저)
     * @param id : 예약 아이디
     * @param request : 기존 예약 정보
     * @return : 승인 및 취소 상태
     */
    @PutMapping("/partner/approval/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateReservation.Response updateReservation(
            @PathVariable Long id,
            @RequestBody UpdateReservation.Request request
    ){
        return UpdateReservation.Response.from(
                reservationService.updateReservation(id, request)
        );
    }

    /**
     * 예약 리스트
     * @param id: 매니저 아이디
     * @return 매장 예약 리스트
     */
    @GetMapping("/partner/reservation-list/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public SearchReservation getReservation(
            @PathVariable Long id
    ){
        return SearchReservation.from(
                reservationService.searchReservation(id)
        );
    }

    /**
     * 매장 도착 확인 여부 변경
     * @param id: 예약 아이디
     * @param request: 예약 고객 정보
     * @return : 예약 완료 정보
     */
    @PutMapping("/kiosk/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateArrive.Response updateArrive(
            @PathVariable Long id,
            @RequestBody UpdateArrive.Request request
    ){

        return UpdateArrive.Response.from(
                reservationService.updateArrive(id, request)
        );
    }

    /**
     * 예약 취소
     * @param reservationId : 예약 아이디
     * @return 예약 취소 정보
     */
    @DeleteMapping("/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> cancelReservation(
            @RequestParam(name = "reservationId") Long reservationId
    ){
        return ResponseEntity.ok(
                reservationService.cancelReservation(reservationId)
        );
    }
}
