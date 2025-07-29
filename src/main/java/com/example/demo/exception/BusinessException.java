package com.example.demo.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
}