package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    EMAIL_EXISTS("EMAIL_EXISTS", "Email already exists"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}