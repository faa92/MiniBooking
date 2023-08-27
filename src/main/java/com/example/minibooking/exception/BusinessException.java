package com.example.minibooking.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super((message));
    }
}
