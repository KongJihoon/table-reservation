package com.zerobase.tablereservation.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignUpManager {
    private String name;
    private String email;
    private String password;
    private String phone;

    public SignUpManager from(ManagerDto managerDto) {
        return SignUpManager.builder()
                .name(managerDto.getName())
                .email(managerDto.getEmail())
                .password(managerDto.getPassword())
                .phone(managerDto.getPhone())
                .build();
    }
}
