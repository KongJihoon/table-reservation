package com.zerobase.tablereservation.review.service;

import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.customer.entity.CustomerRepository;
import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.reservation.entity.Reservation;
import com.zerobase.tablereservation.reservation.entity.ReservationRepository;
import com.zerobase.tablereservation.review.dto.CreateReview;
import com.zerobase.tablereservation.review.dto.ReviewDto;
import com.zerobase.tablereservation.review.dto.UpdateReview;
import com.zerobase.tablereservation.review.entity.Review;
import com.zerobase.tablereservation.review.entity.ReviewRepository;
import com.zerobase.tablereservation.shop.entity.Shop;
import com.zerobase.tablereservation.shop.entity.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.tablereservation.global.type.ErrorCode.*;
import static com.zerobase.tablereservation.reservation.type.ReservationStatus.USE_COMPLETED;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
        private final CustomerRepository customerRepository;
        private final ReservationRepository reservationRepository;
        private final ReviewRepository reviewRepository;
        private final ShopRepository shopRepository;

    @Override
    @Transactional
    public ReviewDto createReview(Long customerId, Long shopId, Long reservationId, CreateReview.Request request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new CustomException(SHOP_NOT_FOUND));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validationReviewStatus(customer, reservation);

        Review review = reviewRepository.save(Review.builder()
                .reviewContent(request.getReviewContent())
                .rating(request.getRating())
                .customer(customer)
                .shop(shop)
                .reservation(reservation)
                .build());



        return ReviewDto.fromEntity(review);
    }

    @Override
    @Transactional
    public ReviewDto updateReview(Long reviewId, UpdateReview.Request request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));

        validationUpdateReview(request);

        review.setRating(request.getRating());
        review.setReviewContent(request.getReviewContent());

        Review save = reviewRepository.save(review);

        return ReviewDto.fromEntity(save);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.delete(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND)));
    }


    private void validationReviewStatus(Customer customer, Reservation reservation){

        if(!reservation.getCustomer().getId().equals(customer.getId())){
            throw new CustomException(CUSTOMER_AUTHORITY_NOT_MATCH);
        } else if (reviewRepository.existsByReservationId(reservation.getId())) {
            throw new CustomException(ALREADY_EXIST_REVIEW);
        } else if (!reservation.getReservationStatus().equals(USE_COMPLETED)) {
            throw new CustomException(REVIEW_NOT_AVAILABLE);
        }

    }

    private void validationUpdateReview(UpdateReview.Request request){
        if(request.getRating() > 5 || request.getRating() < 0){
            throw new CustomException(REVIEW_RATING_OVER);
        } else if (request.getReviewContent().length() > 200) {
            throw new CustomException(REVIEW_CONTENT_TOO_LONG);
        }
    }
}
