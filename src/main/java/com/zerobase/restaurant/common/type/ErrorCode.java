package com.zerobase.restaurant.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    PARTNER_NOT_FOUND("파트너를 찾을 수 없습니다."),
    STORE_NOT_FOUND("매장을 찾을 수 없습니다."),
    STORE_NOT_MATCH_MANAGER("파트너 본인의 매장이 아닙니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    ALREADY_EXISTED_RESTAURANT("이미 사용중인 매장 이름입니다."),
    ALREADY_EXISTED_USER("이미 가입된 회원명입니다.")
    ;

    private final String description;
}
