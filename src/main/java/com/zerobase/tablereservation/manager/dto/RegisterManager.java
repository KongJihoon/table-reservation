package com.zerobase.tablereservation.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterManager {
    private String name;
    private String email;
    private String password;
    private String phone;

    public RegisterManager from(ManagerDto managerDto){
        return RegisterManager.builder()
                .name(managerDto.getName())
                .email(managerDto.getEmail())
                .password(managerDto.getPassword())
                .phone(managerDto.getPhone())
                .build();
    }
}
