package com.itau.app.application.exception;

public class BacenCircuitBrakerUseCaseException extends RuntimeException {
    public BacenCircuitBrakerUseCaseException(String message) {
        super(message);
    }
}
