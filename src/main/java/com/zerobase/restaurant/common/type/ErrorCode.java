package com.zerobase.restaurant.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    USER_NOT_FOUND("회원을 찾을 수 없습니다."),
    PARTNER_NOT_FOUND("파트너를 찾을 수 없습니다."),
    STORE_NOT_FOUND("매장을 찾을 수 없습니다."),
    STORE_NOT_MATCH_MANAGER("파트너 본인의 매장이 아닙니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    ALREADY_EXISTED_RESTAURANT("이미 사용중인 매장 이름입니다."),
    ALREADY_EXISTED_PARTNER("이미 가입된 파트너명입니다."),
    ALREADY_EXISTED_CUSTOMER("이미 가입된 회원명입니다."),
    ALREADY_RESERVED_TIME("이미 예약이 마감된 시간입니다."),
    RESERVATION_NOT_FOUND("예약을 찾을 수 없습니다."),
    RESERVATION_TYPE_ERROR("예약 타입에 문제가 발생했습니다."),
    TOO_EARLY_ARRIVAL_RESERVATION_TIME("예약 시간보다 너무 일찍 도착했습니다."),
    TOO_LATE_ARRIVAL_RESERVATION_TIME("예약 시간보다 너무 늦게 도착했습니다.")
    ;

    private final String description;
}
