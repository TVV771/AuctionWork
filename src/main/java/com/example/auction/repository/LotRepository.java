package com.example.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.auction.model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {

}