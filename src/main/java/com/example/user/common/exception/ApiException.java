package com.example.user.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final UserErrorCode userErrorCode;
    private final String description;

    public ApiException(UserErrorCode userErrorCode){
        super(userErrorCode.getDescription());

        this.userErrorCode = userErrorCode;
        this.description = userErrorCode.getDescription();
    }

    public ApiException(UserErrorCode userErrorCode, String errorMessage){
        super(errorMessage);

        this.userErrorCode = userErrorCode;
        this.description = errorMessage;
    }

    public ApiException(UserErrorCode userErrorCode, Throwable tx){
        super(tx);

        this.userErrorCode = userErrorCode;
        this.description = userErrorCode.getDescription();
    }

    public ApiException(UserErrorCode userErrorCode, Throwable tx, String errorMessage){
        super(tx);

        this.userErrorCode = userErrorCode;
        this.description = errorMessage;
    }

}
