package com.zerobase.restaurant.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationType {

    PENDING("예약 대기"),
    APPROVAL("예약 승인"),
    CANCELED("예약 취소"),
    CHECK_IN("이용 완료");

    private final String description;
}
