package com.zerobase.tablereservation.auth.controller;

import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.global.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zerobase.tablereservation.global.type.ErrorCode.INVALID_ACCESS_TOKEN;
import static com.zerobase.tablereservation.global.type.ErrorCode.JWT_TOKEN_WRONG_TYPE;

@Slf4j
@RestController
public class SecurityController {

    @GetMapping("/exception/accessDenied")
    public void accessDenied(){
        throw new CustomException(INVALID_ACCESS_TOKEN);
    }

    @GetMapping("/exception/unaccessDenied")
    public void unAccessDenied(){
        throw new CustomException(JWT_TOKEN_WRONG_TYPE);
    }

}
