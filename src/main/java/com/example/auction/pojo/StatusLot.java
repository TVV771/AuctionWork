package com.example.auction.pojo;



public enum StatusLot {
    STARTED("STARTED"),
    STOPPED("STOPPED"),
    CREATED("CREATED");

    private final String status;
    StatusLot(String status) {
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}