package com.example.user.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User의 경우 800번대 에러코드 사용
 */

@AllArgsConstructor
@Getter
public enum UserErrorCode {

    OK(HttpStatus.OK, "200", "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "잘못된 요청"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "804", "회원 정보를 찾을 수 없습니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 내부 오류"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR, "512", "Null point error")
    ;

    private final HttpStatus httpStatus; //Http 상태코드
    private final String errorCode; // 에러코드
    private final String description; //설명
}
