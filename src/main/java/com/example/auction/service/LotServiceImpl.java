package com.example.auction.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.auction.dto.*;
import com.example.auction.exception.DataException;
import com.example.auction.exception.NotIdException;
import com.example.auction.exception.StatusException;
import com.example.auction.pojo.LotModel;
import com.example.auction.pojo.StatusLot;
import com.example.auction.repository.BidRepository;
import com.example.auction.repository.LotRepository;
import com.example.auction.service.maping.BidMap;
import com.example.auction.pojo.BidModel;
import com.example.auction.service.maping.LotMap;

import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@Service
public class LotServiceImpl implements LotService {
    @Autowired
    private final LotRepository lotRepository;
    @Autowired
    private final BidRepository bidRepository;

    private final String[] HEADERS = {"id", "title", "description", "status", "lastBidder", "currentPrice"};

    @Override
    public Bid getBidFirstToId(int id) {
        return BidMap.mapToBid(bidRepository.getFirstByBidDateContainingOrderByLotID(id).orElseThrow(NotIdException::new));
    }

    @Override
    public Bid getBidFrequentToId(int id) {
        return BidMap.mapToBid(bidRepository.getBidFrequentToId(id).orElseThrow(NotIdException::new));
    }

    @Override
    public FullLot getFullLot(int id) {
        return lotRepository.getFullLot(id);
    }

    @Override
    public ResponseEntity<?> startBidding(int id) {
        LotModel lotModel = lotRepository.findById(id).orElseThrow(NotIdException::new);
        lotModel.setStatus(StatusLot.STARTED);
        lotRepository.save(lotModel);
        return new ResponseEntity<>("Лот переведен в статус начато", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> placeABet(int id, String bidderName) {
        LotModel lotModel = lotRepository.findById(id).orElseThrow(NotIdException::new);
        if (lotModel.getStatus().equals(StatusLot.STARTED)) {
            BidModel bidModel = new BidModel(bidderName, lotModel);
            bidRepository.save(bidModel);
            return new ResponseEntity<>("Ставка создана " + bidderName, HttpStatus.OK);
        } else throw new StatusException();
    }

    @Override
    public ResponseEntity<?> stopBidding(int id) {
        LotModel lotModel = lotRepository.findById(id).orElseThrow(NotIdException::new);
        lotModel.setStatus(StatusLot.STOPPED);
        lotRepository.save(lotModel);
        return new ResponseEntity<>("Лот перемещен в статус остановлен", HttpStatus.OK);
    }

    @Override
    public Lot createLot(CreateLot lot) {
        if (CheckCreateLot.checkCreateLot(lot)) {
            LotModel lotModel = LotMap.mapToLotModel(lot);
            lotRepository.save(lotModel);
            return LotMap.mapToLot(lotModel);
        }
        throw new DataException();
    }

    @Override
    public Set<Lot> getSetLot(String status, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<LotModel> lotModelPage = lotRepository.findAll(pageable);
        Set<Lot> lots = lotModelPage.stream().map(LotMap::mapToLot).collect(Collectors.toSet());
        return lots.stream().filter(lot -> lot.getStatus().getStatus().equals(status)).collect(Collectors.toSet());
    }

    @Override
    public void getFile() {
//        Iterable<FullLotNov> fullLots = lotRepository.getFullLots();
//        Set<FullLot> fullLots = new HashSet<>();
//        for (LotModel lotModel : lotModels) {
//            fullLots.add(FullLotMap.mapToFullLot(lotModel));
//        }
//        StringWriter sw = new StringWriter();
//        CSVFormat format = CSVFormat.DEFAULT.builder()
//                .setHeader(HEADERS)
//                .build();
//        Path path = Paths.get("src/main/resources/lot_info" + ".csv");
//        try (
//                BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
//                CSVPrinter printer = new CSVPrinter(writer, format);
//        ) {
//            printer.printRecords();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}