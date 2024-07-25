package com.zerobase.restaurant.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationType {

    NONE("예약 없음"),
    CANCELED("예약 취소"),
    CHECK_IN("이용 완료");

    private final String description;
}
