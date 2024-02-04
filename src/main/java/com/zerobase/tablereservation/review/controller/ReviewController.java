package com.zerobase.tablereservation.review.controller;

import com.zerobase.tablereservation.review.dto.CreateReview;
import com.zerobase.tablereservation.review.dto.UpdateReview;
import com.zerobase.tablereservation.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;


    /**
     * 리뷰 작성
     * @param customerId
     * @param shopId
     * @param reservationId
     * @param request
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReview.Response response(
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "shopId") Long shopId,
            @RequestParam(name = "reservationId") Long reservationId,
            @RequestBody CreateReview.Request request
    ){

        return CreateReview.Response.from(
                reviewService.createReview(customerId, shopId, reservationId, request)
        );
    }


    /**
     * 리뷰 수정
     * @param reviewId
     * @param request
     * @return
     */
    @PutMapping("/update/{reviewId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateReview.Response response(
            @PathVariable Long reviewId,
            @RequestBody UpdateReview.Request request
    ){
        return UpdateReview.Response.from(
                reviewService.updateReview(reviewId, request)
        );
    }


    /**
     * 리뷰 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> deleteReview(
            @PathVariable Long id
    ){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("리뷰 삭제 완료");
    }

}
