package com.zerobase.restaurant.common.exception;

import com.zerobase.restaurant.common.type.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
