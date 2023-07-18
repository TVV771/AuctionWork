package com.example.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.auction.dto.BidDTO;
import com.example.auction.dto.LotStatus;
import com.example.auction.model.Bid;
import com.example.auction.model.Lot;
import com.example.auction.repository.BidRepository;
import com.example.auction.repository.LotRepository;

import java.io.IOException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final LotRepository lotRepository;

    @Override
    public BidDTO getFirstBidder(Integer id) throws IOException {
        return BidDTO.fromBid(bidRepository.findAllBidByLot(id).stream()
                .min(Comparator.comparing(Bid::getBidDate))
                .orElse(new Bid("не найден")));
    }

    @Override
    public BidDTO getMostFrequentBidder(Integer id) throws IOException {
        return BidDTO.fromBid(bidRepository.findAllBidByLot(id).stream()
                .max(Comparator.comparing(Bid::getBidDate))
                .orElse(new Bid("не найден")));
    }

    @Override
    public ResponseEntity<?> createBid(Integer id, String name) throws IOException {
        Lot lot = lotRepository.findById(id).orElseThrow(IOException::new);
        if (lot.getStatus() == LotStatus.valueOf("STARTED")) {
            Bid bid = new Bid();
            bid.setLot(lot);
            bid.setBidDate(bid.getBidDate());
            bid.setBidderName(name);
            bidRepository.save(bid);
            return new ResponseEntity<>("Ставка создана", HttpStatus.OK);
        } else return new ResponseEntity<>("Лот в неверном статусе", HttpStatus.BAD_REQUEST);
    }
}