package com.example.auction.service.maping;


import com.example.auction.dto.CreateLot;
import com.example.auction.dto.Lot;
import com.example.auction.pojo.LotModel;
import com.example.auction.pojo.StatusLot;

public class LotMap {
    public static LotModel mapToLotModel(CreateLot lot) {
        return LotModel.builder()
                .title(lot.getTitle())
                .status(StatusLot.CREATED)
                .description(lot.getDescription())
                .startPrice(lot.getStartPrice())
                .bidPrice(lot.getBidPrice())
                .build();
    }

    public static Lot mapToLot(LotModel lotModel) {
        return Lot.builder()
                .title(lotModel.getTitle())
                .status(lotModel.getStatus())
                .description(lotModel.getDescription())
                .startPrice(lotModel.getStartPrice())
                .bidPrice(lotModel.getBidPrice())
                .build();
    }
}