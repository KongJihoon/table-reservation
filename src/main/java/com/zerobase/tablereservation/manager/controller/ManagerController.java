package com.zerobase.tablereservation.manager.controller;

import com.zerobase.tablereservation.auth.dto.LogIn;
import com.zerobase.tablereservation.auth.security.TokenProvider;
import com.zerobase.tablereservation.auth.service.AuthService;
import com.zerobase.tablereservation.manager.dto.SignUpManager;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final AuthService authService;
    private final TokenProvider tokenProvider;

    /**
     * 매니저 회원 가입
     * @param request : register
     * @return 매니저 정보
     */
    @PostMapping("/signup/manager")
    public ResponseEntity<?> managerSignUp(@RequestBody SignUpManager request){
        return ResponseEntity.ok().body(
                request.from(managerService.register(request)));
    }

    /**
     * 매니저 로그인
     * @param input : 이메일, 패스워드
     * @return : 로그인 완료 토큰
     */
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
    /**
     * 매니저 정보 조회
     * @param id 매니저 아이디
     * @return 매니저 정보
     */
    @GetMapping("/partner/detail")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> getManagerDetail(
            @RequestParam("id") Long id
    ){
        return ResponseEntity.ok(managerService.managerDetail(id));
    }
}
