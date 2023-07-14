package com.example.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LotExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> hendleNotIdException(NotIdException notIdException) {
        String message = "Лот не найден\n";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> hendleStatusException(StatusException statusException) {
        String message = "Лот в неверном статусе\n";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> hendleDataException(DataException dataException) {
        String message = "Лот передан с ошибкой\n";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}