package com.zerobase.tablereservation.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * manager & customer
     */
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST.value(), "이미 가입된 회원입니다."),
    MANAGER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "매니저를 찾을 수 없습니다."),
    CUSTOMER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "고객을 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "패스워드가 일치하지 않습니다."),

    /**
     * shop
     */
    ALREADY_EXIST_SHOP(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 매장입니다."),


    /**
     * security error
     */
    WRONG_TOKEN(HttpStatus.BAD_REQUEST.value(), "잘못된 토큰입니다."),
    TOKEN_TIME_OUT(HttpStatus.CONFLICT.value(), "만료된 JWT 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST.value(), "지원되지 않는 토큰입니다."),
    JWT_TOKEN_WRONG_TYPE(HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 구성의 JWT 토큰입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.FORBIDDEN.value(), "접근 권한이 없습니다."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED.value(), "로그인이 되지 않았습니다."),
    WRONG_TYPE_SIGNATURE(HttpStatus.UNAUTHORIZED.value(), "잘못된 JWT 서명입니다.");
    ;



    private final int status;
    private final String description;

}
