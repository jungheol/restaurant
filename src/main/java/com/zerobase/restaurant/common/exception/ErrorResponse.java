package com.zerobase.restaurant.common.exception;

import com.zerobase.restaurant.common.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private ErrorCode errorcode;
    private String errorMessage;
}
