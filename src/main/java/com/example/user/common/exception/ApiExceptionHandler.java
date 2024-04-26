package com.example.user.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exception(MethodArgumentNotValidException e){

        log.error("", e);

        //에러가 발생한 필드와 메시지 추출
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResult errorResult = ErrorResult.builder()
                .errorCode(UserErrorCode.BAD_REQUEST.getErrorCode())
                .description(UserErrorCode.BAD_REQUEST.getDescription())
                .message("데이터 유효성 검증에 실패하였습니다.")
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
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
