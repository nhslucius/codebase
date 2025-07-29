package com.example.demo.exception;

import com.example.demo.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) {
        ex.printStackTrace();
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error("VALIDATION_FAILED", errors.toString()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolation(ConstraintViolationException ex) {
        ex.printStackTrace();
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(cv -> {
                    String path = cv.getPropertyPath().toString();
                    String field = path.substring(path.lastIndexOf('.') + 1);
                    errors.put(field, cv.getMessage());
                });
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error("QUERY_PARAM_INVALID", errors.toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error("INTERNAL_ERROR", ex.getMessage()));
    }
}