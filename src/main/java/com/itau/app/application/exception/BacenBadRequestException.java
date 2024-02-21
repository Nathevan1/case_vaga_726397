package com.itau.app.application.exception;

public class BacenBadRequestException extends RuntimeException{
    public BacenBadRequestException(String message) {
        super(message);
    }
}
