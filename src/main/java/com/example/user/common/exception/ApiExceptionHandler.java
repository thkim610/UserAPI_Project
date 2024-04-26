package com.example.user.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.example.user")
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ErrorResult> exception(ApiException e){

        log.error("", e);

        ErrorResult errorResult = ErrorResult.builder()
                .errorCode(e.getUserErrorCode().getErrorCode())
                .description(e.getDescription())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResult> exception(Exception e){

        log.error("", e);
        ErrorResult errorResult = ErrorResult.builder()
                .errorCode(UserErrorCode.SERVER_ERROR.getErrorCode())
                .description(UserErrorCode.SERVER_ERROR.getDescription())
                .message("서버 내부에 오류가 발생했습니다.")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }
}
