package com.example.auction.service.maping;


import com.example.auction.dto.Bid;

public class BidMap {
    public static Bid mapToBid(com.example.auction.pojo.BidModel bidModel){
        return Bid.builder()
                .bidderName(bidModel.getBidderName())
                .bidDate(bidModel.getBidDate())
                .build();
    }
}