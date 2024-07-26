package com.zerobase.restaurant.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApprovedType {

    PENDING("예약 대기"),
    APPROVED("예약 승인"),
    REJECTED("예약 거절");

    private final String description;
}
