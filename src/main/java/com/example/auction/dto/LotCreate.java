package com.example.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.example.auction.model.Lot;

import java.io.Serializable;
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LotCreate implements Serializable {
    private String title;
    private String description;
    private int startPrice;
    private int bidPrice;

    public Lot toLot() {
        Lot lot=new Lot();
        lot.setTitle(this.getTitle());
        lot.setDescription(this.getDescription());
        lot.setStartPrice(this.getStartPrice());
        lot.setBidPrice(this.getBidPrice());
        return lot;
    }
}