package com.zerobase.tablereservation.manager.controller;

import com.zerobase.tablereservation.manager.dto.RegisterManager;
import com.zerobase.tablereservation.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/signup/manager")
    public ResponseEntity<?> register(@RequestBody RegisterManager request){
        return ResponseEntity.ok().body(
                request.from(managerService.register(request))
        );
    }
}
