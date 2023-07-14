package com.example.auction.exception;


public class DataException extends RuntimeException{
    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
    }
}