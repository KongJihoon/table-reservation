package com.zerobase.tablereservation.customer.controller;

import com.zerobase.tablereservation.auth.dto.LogIn;
import com.zerobase.tablereservation.auth.security.TokenProvider;
import com.zerobase.tablereservation.auth.service.AuthService;
import com.zerobase.tablereservation.customer.dto.SignUpCustomer;
import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final AuthService authService;
    private final TokenProvider tokenProvider;


    /**
     * 고객 회원가입
     * @param request : register
     * @return 회원 정보
     */
    @PostMapping("/signup/customer")
    public ResponseEntity<?> customerSignUp(@RequestBody SignUpCustomer request){

        return ResponseEntity.ok().body(
                request.from(customerService.register(request))
        );

    }

    /**
     * 고객 로그인
     * @param input : 이메일, 패스워드
     * @return : 로그인 완료 토큰
     */
    @PostMapping("/signin/customer")
    public ResponseEntity<?> customerSignIn(@RequestBody @Valid LogIn input){
       Customer customer = this.authService.authenticationCustomer(input);
       return ResponseEntity.ok(
               this.tokenProvider.createToken(
                       customer.getEmail(),
                       customer.getUserType()
               )
       );
    }

    /**
     * 고객 정보 조회(매니저, 고객 모두 확인 가능)
     * @param id 고객 아이디
     * @return 고객 정보
     */
    @GetMapping("/customer/detail")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> getCustomerDetail(
            @RequestParam("id") Long id
    ){
        return ResponseEntity.ok(customerService.customerDetail(id));
    }

}
