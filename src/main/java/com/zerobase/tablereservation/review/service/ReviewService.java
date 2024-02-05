package com.zerobase.tablereservation.review.service;

import com.zerobase.tablereservation.review.dto.CreateReview;
import com.zerobase.tablereservation.review.dto.ReviewDto;
import com.zerobase.tablereservation.review.dto.UpdateReview;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(Long customerId, Long shopId, Long reservationId, CreateReview.Request request);


    ReviewDto updateReview(Long reviewId, UpdateReview.Request request);

    void deleteReview(Long reviewId);


}
