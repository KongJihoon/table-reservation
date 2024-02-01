package com.zerobase.tablereservation.manager.controller;

import com.zerobase.tablereservation.auth.dto.LogIn;
import com.zerobase.tablereservation.auth.security.TokenProvider;
import com.zerobase.tablereservation.auth.service.AuthService;
import com.zerobase.tablereservation.manager.dto.SignUpManager;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup/manager")
    public ResponseEntity<?> managerSignUp(@RequestBody SignUpManager request){
        return ResponseEntity.ok().body(
                request.from(managerService.register(request)));
    }
    @PostMapping("/signin/manager")
    public ResponseEntity<?> managerSignIn(@RequestBody @Valid LogIn input){
        Manager manager = this.authService.authenticatedManager(input);
        return ResponseEntity.ok(
                this.tokenProvider.createToken(
                        manager.getEmail(),
                        manager.getUserType()
                )
        );
    }
}
