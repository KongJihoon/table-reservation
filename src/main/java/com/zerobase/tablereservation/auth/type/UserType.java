package com.zerobase.tablereservation.auth.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    CUSTOMER("ROLE_CUSTOMER"),
    PARTNER("ROLE_PARTNER");


    private final String description;
}
