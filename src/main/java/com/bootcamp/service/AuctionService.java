package com.bootcamp.service;


import com.bootcamp.model.AdStatus;
import com.bootcamp.model.AuctionAd;
import com.bootcamp.repo.AuctionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AuctionService {


    @Autowired
    private AuctionRepo auctionRepo;

//    @Autowired
//    private EmailService emailService;

    public AuctionAd createAd(AuctionAd auctionAd){


        return auctionRepo.save(auctionAd);

    }

    public AuctionAd makeBet(Long id, Long price, String bidderUserName){


        AuctionAd auctionAd = auctionRepo.findById(id).orElse(null);

        if (auctionAd != null){

            auctionAd.setNewPrice(price);
            auctionAd.setLatesBidersUserName(bidderUserName);
            auctionAd.setLatestBetTime(new Date());
            auctionRepo.save(auctionAd);
        }


        assert auctionAd != null;
        log.info( "message = {}", auctionAd.getLatesBidersUserName() +
                " ВАША СТАВКА НА ПРОДУКТ " + auctionAd.getProduct_name() + " БЫЛА ПЕРЕБИТА\n" +
                "НОВАЯ СУММА " + auctionAd.getNewPrice());

         return auctionRepo.saveAndFlush(auctionAd);

    }

    @Scheduled(fixedRate = 1000 * 60)
    public void closeTheDeal(){

        log.info("WORKING");
        List<AuctionAd> auctionAd = auctionRepo.findAll();

        for(AuctionAd data: auctionAd) {

            LocalDateTime time = LocalDateTime.now();

            if (data.getLatestBetTime() != null) {
                LocalDateTime BetTime = convertToLocalDateTimeViaInstant(data.getLatestBetTime());
                Duration duration = Duration.between(BetTime, time);
                long minutes = duration.toMinutes();

                if (minutes > 3 && data.getAdStatus().equals(AdStatus.ACTIVE)) {

                    data.setAdStatus(AdStatus.NOT_ACTIVE);
                    auctionRepo.save(data);
                    log.info("СДЕЛКА ЗАКРЫТА");
                    log.info("СООБЩЕНИЕ ДЛЯ ВЛАДЕЛЬЦА: {}", "УВАЖЕМЫЙ(-АЯ) " + data.getOwnerId().getUsername() + ", ВАШ ППРОДУКТ "
                            + data.getProduct_name() + " БЫЛ ПРОДАН ЗА " + data.getNewPrice());
                    log.info("СООБЩЕНИЕ ДЛЯ ПОКУПАТЕЛЯ: {}", "УВАЖЕМЫЙ(-АЯ) " + data.getLatesBidersUserName() + ", ВАША СТАВКА НА СУММУ "
                            + data.getNewPrice() + "ОКАЗАЛАСЬ ПОБЕДНОЙ! МОЖЕТЕ ЗАБРАТЬ ТОВАР: " +
                            data.getProduct_name());
                }
            }
        }
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public List<AuctionAd> getListOfAdds(AdStatus status){


//        List<AuctionAd> auctionAds = auctionRepo.findAll();
//        auctionAds.get(0).getUserId().getId();

        return auctionRepo.findAllByAdStatus(status);
    }
    


}
