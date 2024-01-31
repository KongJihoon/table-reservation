package com.zerobase.tablereservation.manager.service;


import com.zerobase.tablereservation.auth.type.UserType;
import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.global.type.ErrorCode;
import com.zerobase.tablereservation.manager.dto.ManagerDto;
import com.zerobase.tablereservation.manager.dto.RegisterManager;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.manager.entity.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.tablereservation.global.type.ErrorCode.ALREADY_EXIST_USER;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final PasswordEncoder encoder;
    private final ManagerRepository managerRepository;

    @Override
    @Transactional
    public ManagerDto register(RegisterManager manager) {
        boolean exist = managerRepository.existsByEmail(manager.getEmail());

        if(exist){
            throw new CustomException(ALREADY_EXIST_USER);
        }

        manager.setPassword(encoder.encode(manager.getPassword()));

        Manager setManager = managerRepository.save(Manager.builder()
                .name(manager.getName())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phone(manager.getPhone())
                .userType(UserType.PARTNER)
                .build());

        return ManagerDto.fromEntity(setManager);
    }
}
