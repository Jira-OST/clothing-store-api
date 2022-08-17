package com.example.clothingstoreapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleResourceNotFound(final ProductNotFoundException exception,
                                                              final HttpServletRequest request) {

        return new ErrorResponse(Errors.PRODUCT_NOT_FOUND.getMessage(),
                Errors.PRODUCT_NOT_FOUND.getCode());
    }
}