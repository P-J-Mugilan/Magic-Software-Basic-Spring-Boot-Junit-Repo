package com.magicsoftware.exception;

import com.magicsoftware.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.TimeZone;


@RestControllerAdvice
public class GlobalClassException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception ex){
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
        );
    }
}
