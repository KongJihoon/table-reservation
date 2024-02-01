package com.zerobase.tablereservation.customer.controller;

import com.zerobase.tablereservation.auth.dto.LogIn;
import com.zerobase.tablereservation.auth.security.TokenProvider;
import com.zerobase.tablereservation.auth.service.AuthService;
import com.zerobase.tablereservation.customer.dto.SignUpCustomer;
import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.customer.service.CustomerService;
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
public class CustomerController {
    private final CustomerService customerService;
    private final AuthService authService;
    private final TokenProvider tokenProvider;


    @PostMapping("/signup/customer")
    public ResponseEntity<?> customerSignUp(@RequestBody SignUpCustomer request){

        return ResponseEntity.ok().body(
                request.from(customerService.register(request))
        );

    }

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

}
