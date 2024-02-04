package com.zerobase.tablereservation.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CreateReview {


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private String reviewContent;
        private double rating;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{

        private Long reviewId;
        private String reviewContent;
        private double rating;
        private String userName;
        private String shopName;


        public static Response from(ReviewDto reviewDto){
            return Response.builder()
                    .reviewId(reviewDto.getReviewId())
                    .reviewContent(reviewDto.getReviewContent())
                    .rating(reviewDto.getRating())
                    .userName(reviewDto.getUserName())
                    .shopName(reviewDto.getShopName())
                    .build();
        }
    }

}
