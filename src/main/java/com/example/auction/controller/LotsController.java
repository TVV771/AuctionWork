package com.example.auction.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.auction.dto.*;
import com.example.auction.service.LotService;

import java.io.IOException;
import java.util.Set;


@Data
@RestController
@RequestMapping("/lot")
public class LotsController {
    private final LotService lotService;


    //    Возвращает первого ставившего на этот лот
    @GetMapping("/{id}/first")
    Bid getBidFirstToId(@PathVariable int id) {
        return lotService.getBidFirstToId(id);
    }

    //    Возвращает имя ставившего на данный лот наибольшее количество раз
    @GetMapping("/{id}/frequent")
    Bid getBidFrequentToId(@PathVariable int id) {
        return lotService.getBidFrequentToId(id);
    }

    //    Получить полную информацию о лоте
    @GetMapping("/{id}")
    FullLot getFullLot(@PathVariable int id) {
        return lotService.getFullLot(id);
    }

    //    Начать торги по лоту
    @PostMapping("/{id}/start")
    ResponseEntity<?> startBidding(@PathVariable int id) {
        return lotService.startBidding(id);
    }

    //    Сделать ставку по лоту
    @PostMapping("/{id}/bid")
    ResponseEntity<?> placeABet(@PathVariable int id, @RequestBody String bidderName) {
        return lotService.placeABet(id, bidderName);
    }

    //    Остановить торги по лоту
    @PostMapping("/{id}/stop")
    ResponseEntity<?> stopBidding(@PathVariable int id) {
        return lotService.stopBidding(id);
    }

    //    Создает новый лот
    @PostMapping
    Lot createLot(@RequestBody CreateLot lot) {
        return lotService.createLot(lot);
    }

    //    Получить все лоты, основываясь на фильтре статуса и номере страницы
    @GetMapping
    Set<Lot> getSetLot(@RequestParam("status") String status,
                       @RequestParam("page") int page) {
        return lotService.getSetLot(status, page);
    }

    //    Экспортировать все лоты в файл CSV
    @GetMapping("/export")
    void getFile() throws IOException {
        lotService.getFile();
    }
}