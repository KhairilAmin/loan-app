package com.enigma.loan_app.utils;

import com.enigma.loan_app.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchDataExistsException.class)
    public ResponseEntity<CommonResponse<Object>> handleNoSuchDataExistsException(NoSuchDataExistsException exception) {
        CommonResponse<Object> error = CommonResponse.builder().message(exception.getMessage()).data(null).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}