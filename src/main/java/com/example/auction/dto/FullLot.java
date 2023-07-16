package com.example.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.auction.pojo.StatusLot;

@Data
@AllArgsConstructor
public class FullLot {
    private int id;
    private String title;
    private int bidPrice;
    private int startPrice;
    private String description;
    private StatusLot status;
    private int currentPrice;
    private String lastBidName;
    private String lastBidData;

}