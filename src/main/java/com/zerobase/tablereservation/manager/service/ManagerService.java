package com.zerobase.tablereservation.manager.service;

import com.zerobase.tablereservation.manager.dto.ManagerDto;
import com.zerobase.tablereservation.manager.dto.RegisterManager;

public interface ManagerService {


    ManagerDto register(RegisterManager manager);

}
