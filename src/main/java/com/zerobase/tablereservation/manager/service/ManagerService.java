package com.zerobase.tablereservation.manager.service;

import com.zerobase.tablereservation.manager.dto.ManagerDto;
import com.zerobase.tablereservation.manager.dto.SignUpManager;


public interface ManagerService {


    /**
     * 회원 가입
     */
    ManagerDto register(SignUpManager manager);

    /**
     * 매니저 정보 확인
     */
    ManagerDto managerDetail(Long managerId);

}
