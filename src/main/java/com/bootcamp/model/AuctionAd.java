package com.bootcamp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Auction_Ad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String product_name;
    private String description;

    @CreationTimestamp
    private Date publicationDate;

    private Date latestBetTime;
    @Enumerated(EnumType.STRING)
    private AdStatus adStatus = AdStatus.ACTIVE;
    private Long minimumPrice;
    private Long newPrice;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User ownerId;

    private String latesBidersUserName = "";
}
