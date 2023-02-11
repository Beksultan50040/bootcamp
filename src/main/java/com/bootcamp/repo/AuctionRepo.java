package com.bootcamp.repo;

import com.bootcamp.model.AdStatus;
import com.bootcamp.model.AuctionAd;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AuctionRepo extends JpaRepository<AuctionAd, Long> {


    List<AuctionAd> findAllByAdStatus(AdStatus status);
}
