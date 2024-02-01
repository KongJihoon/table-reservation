package com.zerobase.tablereservation.manager.service;

import com.zerobase.tablereservation.manager.dto.ManagerDto;
import com.zerobase.tablereservation.manager.dto.SignUpManager;


public interface ManagerService {


    ManagerDto register(SignUpManager manager);

}
