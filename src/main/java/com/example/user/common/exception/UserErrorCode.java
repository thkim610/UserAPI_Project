package com.example.user.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 커스텀 에러코드 사용
 * U - User 관련 에러 코드
 * R - 요청(request) 관련 에러코드
 * S - 서버 내부 오류 관련 에러코드
 */

@AllArgsConstructor
@Getter
public enum UserErrorCode {

    OK(HttpStatus.OK, "200", "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "R400", "잘못된 요청"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "U404", "회원 정보를 찾을 수 없습니다."),
    USER_DUPLICATED(HttpStatus.BAD_REQUEST, "U405", "중복된 회원 ID"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S500", "서버 내부 오류"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR, "R512", "Null point error")
    ;

    private final HttpStatus httpStatus; //Http 상태코드
    private final String errorCode; // 에러코드
    private final String description; //설명
}
