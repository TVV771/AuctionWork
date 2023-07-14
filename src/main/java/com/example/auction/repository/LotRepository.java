package com.example.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.auction.dto.FullLot;
import com.example.auction.pojo.LotModel;


@Repository
public interface LotRepository extends CrudRepository<LotModel, Integer>, PagingAndSortingRepository<LotModel, Integer> {

    @Query(value = "select l.lot_id, l.title, l.bid_price , l.start_price , l.description , l.status , " +
            "(select b.bidder_name from public.bid b where b.lot_id = l.lot_id and b.bid_date = " +
            "(select max(b.bid_date)from public.bid b where b.lot_id = l.lot_id )group by b.bidder_name) bidder_name, " +
            "(select max(b.bid_date)from public.bid b where b.lot_id = l.lot_id ) bid_date, " +
            "(select count(b.id)*l.bid_price+l.start_price from public.bid b where b.lot_id = l.lot_id) currentPrice " +
            "from lot l inner join bid b \n" +
            "on l.lot_id = b.lot_id group by l.title, l.bid_price , l.start_price , l.description , l.status , l.lot_id " +
            "having  l.lot_id = ?1",
            nativeQuery = true)
    FullLot getFullLot(int id);

}