package com.example.auction.exception;

public class StatusException extends RuntimeException{
    public StatusException(String message) {
        super(message);
    }

    public StatusException() {
        super();
    }
}