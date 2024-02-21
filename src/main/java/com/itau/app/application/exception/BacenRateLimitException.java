package com.itau.app.application.exception;

public class BacenRateLimitException extends RuntimeException{
    public BacenRateLimitException(String message) {
        super(message);
    }
}
