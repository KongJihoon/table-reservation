package com.zerobase.tablereservation.review.service;

import com.zerobase.tablereservation.review.dto.CreateReview;
import com.zerobase.tablereservation.review.dto.ReviewDto;
import com.zerobase.tablereservation.review.dto.UpdateReview;

import java.util.List;

public interface ReviewService {

    /**
     * 리뷰 등록
     * @param customerId : 고객 아이디
     * @param shopId : 매장 아이디
     * @param reservationId : 예약 아이디
     * @param request : 리뷰 작성 정보
     * @return : 리뷰 작성
     */
    ReviewDto createReview(Long customerId, Long shopId, Long reservationId, CreateReview.Request request);


    /**
     * 리뷰 수정
     * @param reviewId : 리뷰 아이디
     * @param request : 변경 내용
     * @return : 변경된 정보
     */
    ReviewDto updateReview(Long reviewId, UpdateReview.Request request);

    /**
     * 리뷰 삭제
     * @param reviewId : 리뷰 아이디
     */
    void deleteReview(Long reviewId);


}
