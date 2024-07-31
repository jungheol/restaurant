package com.zerobase.restaurant.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 일반 error
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),

    // 파트너 및 유저 관련 error
    CUSTOMER_NOT_FOUND("회원을 찾을 수 없습니다."),
    PARTNER_NOT_FOUND("파트너를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    ALREADY_EXISTED_PARTNER("이미 가입된 파트너명입니다."),
    ALREADY_EXISTED_CUSTOMER("이미 가입된 회원명입니다."),

    // 식당 관련 error
    STORE_NOT_FOUND("식당을 찾을 수 없습니다."),
    STORE_NOT_MATCH_MANAGER("파트너 본인의 식당이 아닙니다."),
    ALREADY_EXISTED_RESTAURANT("이미 사용중인 식당 이름입니다."),

    // 예약 관련 error
    ALREADY_RESERVED_TIME("이미 예약이 마감된 시간입니다."),
    RESERVATION_NOT_FOUND("예약을 찾을 수 없습니다."),
    RESERVATION_TYPE_ERROR("예약 타입에 문제가 발생했습니다."),
    TOO_EARLY_ARRIVAL_RESERVATION_TIME("예약 시간보다 너무 일찍 도착했습니다."),
    TOO_LATE_ARRIVAL_RESERVATION_TIME("예약 시간보다 너무 늦게 도착했습니다."),

    // 리뷰 관련 error
    REVIEW_NOT_FOUND("리뷰를 찾을 수 없습니다."),
    REVIEW_RATING_OUT_OF_RANGE("리뷰 점수를 1점에서 5점 사이로 입력해주세요."),
    REVIEW_TOO_LONG("리뷰 내용을 조금 줄여주세요."),
    REVIEW_USER_NOT_MATCHED("식당 이용자와 리뷰 작성자가 같지 않습니다."),
    REVIEW_ALREADY_EXIST("해당 예약건에 대한 리뷰가 이미 존재합니다."),
    REVIEW_NOT_AVAILABLE("해당 예약건은 리뷰를 쓸 수 있는 상태가 아닙니다.")
    ;

    private final String description;
}
