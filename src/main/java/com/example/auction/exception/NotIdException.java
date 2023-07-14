package com.example.auction.exception;


public class NotIdException extends RuntimeException{
    public NotIdException(String message) {
        super(message);
    }

    public NotIdException() {
        super();
    }
}