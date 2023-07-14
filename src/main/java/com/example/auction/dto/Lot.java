package com.example.auction.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.auction.pojo.StatusLot;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lot {
    private StatusLot status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
}
