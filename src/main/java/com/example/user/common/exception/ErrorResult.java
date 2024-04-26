package com.example.user.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ErrorResult {
    private String errorCode; //에러코드
    private String description; //설명
    private String message; //세부 메시지

}
