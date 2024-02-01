package com.zerobase.tablereservation.customer.dto;

import com.zerobase.tablereservation.auth.type.UserType;
import com.zerobase.tablereservation.customer.entity.Customer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    private String name;

    private UserType userType;

    private String email;

    private String password;

    private String phone;

    public static CustomerDto fromEntity(Customer customer){

        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .userType(customer.getUserType())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .phone(customer.getPhone())
                .build();
    }

}
