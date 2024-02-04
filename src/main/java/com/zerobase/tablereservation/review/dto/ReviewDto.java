package com.zerobase.tablereservation.review.dto;

import com.zerobase.tablereservation.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDto {

    private Long reviewId;

    private String reviewContent;

    private double rating;

    private String userName;

    private String shopName;

    public static ReviewDto fromEntity(Review review){

        return ReviewDto.builder()
                .reviewId(review.getId())
                .reviewContent(review.getReviewContent())
                .rating(review.getRating())
                .userName(review.getCustomer().getName())
                .shopName(review.getShop().getShopName())
                .build();

    }


}
