package com.bootcamp.controller;


import com.bootcamp.model.AdStatus;
import com.bootcamp.model.AuctionAd;
import com.bootcamp.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("ad/{status}")
    private List<AuctionAd> getListOfAdds(@PathVariable AdStatus status){

        return auctionService.getListOfAdds(status);
    }

    @PostMapping("ad")
    public AuctionAd createAd( @RequestBody AuctionAd auctionAd){

        return auctionService.createAd(auctionAd);

    }

    @PostMapping("bet/{productId}")
    public AuctionAd makeBet(@PathVariable Long productId, @RequestParam Long newPrice, @RequestParam String bidderUserName){

        return auctionService.makeBet(productId, newPrice, bidderUserName);
    }

}
