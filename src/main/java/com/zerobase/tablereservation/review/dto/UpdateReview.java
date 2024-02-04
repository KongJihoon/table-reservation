package com.zerobase.tablereservation.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UpdateReview {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{

        private String reviewContent;
        private double rating;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long reviewId;
        private String reviewContent;
        private double rating;


        public static Response from(ReviewDto reviewDto){
            return Response.builder()
                    .reviewId(reviewDto.getReviewId())
                    .reviewContent(reviewDto.getReviewContent())
                    .rating(reviewDto.getRating())
                    .build();
        }

    }
}
