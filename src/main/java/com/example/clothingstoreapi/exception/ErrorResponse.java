package com.example.clothingstoreapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String errorMessage;
    private String errorCode;
    private String details;

    public ErrorResponse(HttpStatus httpStatus, String errorMessage, String details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.errorMessage = errorMessage;
        this.details = details;
    }


}