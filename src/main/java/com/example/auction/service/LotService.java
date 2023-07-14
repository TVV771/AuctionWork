package com.example.auction.service;


import org.springframework.http.ResponseEntity;
import com.example.auction.dto.*;

import java.io.IOException;
import java.util.Set;

public interface LotService {

    //    Возвращает первого ставившего на этот лот
    Bid getBidFirstToId(int id);

    //    Возвращает имя ставившего на данный лот наибольшее количество раз
    Bid getBidFrequentToId(int id);

    //    Получить полную информацию о лоте
    FullLot getFullLot(int id);

    //    Начать торги по лоту
    ResponseEntity<?> startBidding(int id);

    //    Сделать ставку по лоту
    ResponseEntity<?> placeABet(int id, String bidderName);

    //    Остановить торги по лоту
    ResponseEntity<?> stopBidding(int id);

    //    Создает новый лот
    Lot createLot(CreateLot lot);

    //    Получить все лоты, основываясь на фильтре статуса и номере страницы
    Set<Lot> getSetLot(String status, int page);

    //    Экспортировать все лоты в файл CSV
    void getFile() throws IOException;

}