package com.itau.app.adapter.exception;

import com.itau.app.application.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidacaoTransferenciaException.class)
    public ResponseEntity<String> handleMinhaValidacaoException(ValidacaoTransferenciaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BacenRateLimitException.class)
    public ResponseEntity<String> handleBacenRateLimitException(BacenRateLimitException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.getMessage());
    }

    @ExceptionHandler(BacenBadRequestException.class)
    public ResponseEntity<String> handleBacenBadRequestException(BacenBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BacenTimeOutException.class)
    public ResponseEntity<String> handleBacenTimeOutException(BacenTimeOutException ex) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(ex.getMessage());
    }

    @ExceptionHandler(ConsultaSaldoUseCaseException.class)
    public ResponseEntity<String> handleConsultaSaldoUseCaseException(ConsultaSaldoUseCaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TransferenciaUseCaseException.class)
    public ResponseEntity<String> handleTransferenciaUseCaseException(TransferenciaUseCaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BacenCircuitBrakerUseCaseException.class)
    public ResponseEntity<String> handleBacenCircuitBrakerUseCaseException(BacenCircuitBrakerUseCaseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
