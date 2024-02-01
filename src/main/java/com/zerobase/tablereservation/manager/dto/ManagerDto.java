package com.zerobase.tablereservation.manager.dto;

import com.zerobase.tablereservation.auth.type.UserType;
import com.zerobase.tablereservation.manager.entity.Manager;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    private Long id;

    private String name;

    private UserType userType;

    private String email;

    private String password;

    private String phone;

    public static ManagerDto fromEntity(Manager manager){

        return ManagerDto.builder()
                .id(manager.getId())
                .name(manager.getUsername())
                .userType(manager.getUserType())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phone(manager.getPhone())
                .build();
    }


}
