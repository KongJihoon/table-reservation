package com.zerobase.tablereservation.customer.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignUpCustomer {

    private String name;

    private String email;

    private String password;

    private String phone;

    public SignUpCustomer from(CustomerDto customerDto){

        return SignUpCustomer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .phone(customerDto.getPhone())
                .build();
    }

}
