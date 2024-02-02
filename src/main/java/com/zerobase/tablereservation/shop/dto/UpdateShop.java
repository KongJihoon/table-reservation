package com.zerobase.tablereservation.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UpdateShop {



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{

        private Long managerId;

        @NotBlank
        private String shopName;

        @NotBlank
        private String location;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{

        private String shopName;
        private String location;

        public static Response from(ShopDto shopDto){

            return Response.builder()
                    .shopName(shopDto.getShopName())
                    .location(shopDto.getLocation())
                    .build();
        }


    }

}
