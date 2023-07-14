package com.example.auction.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.auction.pojo.BidModel;

import java.util.Optional;

@Repository
public interface BidRepository extends CrudRepository<BidModel, Integer> {

    @Query(value = "select * from bid where lot_id = ?1 order by bid_date limit 1",
            nativeQuery = true)
    Optional <BidModel> getFirstByBidDateContainingOrderByLotID(int lotId);

    @Query(value = "select * from bid where bidder_name = " +
            "(SELECT bidder_name FROM bid " +
            "where lot_id = ?1 " +
            "GROUP BY bidder_name order by count(bidder_name) desc limit 1) " +
            "limit 1"
            , nativeQuery = true)
    Optional<BidModel> getBidFrequentToId(int id);
}