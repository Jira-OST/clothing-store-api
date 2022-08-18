package com.example.clothingstoreapi.exception;

public enum Errors {

    PRODUCT_NOT_FOUND("0001", "Product not exists");

    private final String code;
    private final String message;

    private Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

