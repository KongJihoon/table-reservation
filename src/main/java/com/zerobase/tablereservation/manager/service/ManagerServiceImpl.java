package com.zerobase.tablereservation.manager.service;


import com.zerobase.tablereservation.auth.type.UserType;
import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.manager.dto.ManagerDto;
import com.zerobase.tablereservation.manager.dto.SignUpManager;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.manager.entity.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.tablereservation.global.type.ErrorCode.ALREADY_EXIST_USER;
import static com.zerobase.tablereservation.global.type.ErrorCode.MANAGER_NOT_FOUND;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final PasswordEncoder encoder;
    private final ManagerRepository managerRepository;

    @Override
    @Transactional
    public ManagerDto register(SignUpManager manager) {
        boolean exist = this.managerRepository.existsByEmail(manager.getEmail());

        if(exist){
            throw new CustomException(ALREADY_EXIST_USER);
        }

        manager.setPassword(this.encoder.encode(manager.getPassword()));

        Manager setManager = this.managerRepository.save(Manager.builder()
                .name(manager.getName())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phone(manager.getPhone())
                .userType(UserType.PARTNER)
                .build());

        return ManagerDto.fromEntity(setManager);
    }

    @Override
    public ManagerDto managerDetail(Long managerId) {

        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));

        return ManagerDto.fromEntity(manager);
    }
}
