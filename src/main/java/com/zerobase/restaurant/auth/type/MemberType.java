package com.zerobase.restaurant.auth.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

    CUSTOMER("ROLE_CUSTOMER"),
    PARTNER("ROLE_PARTNER");

    private final String description;
}
