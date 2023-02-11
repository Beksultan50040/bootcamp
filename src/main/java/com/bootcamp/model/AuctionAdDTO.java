package com.bootcamp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionAdDTO {


    private Long id;
    private String product_name;
    private String description;

    private Date publicationDate;

    private AdStatus adStatus = AdStatus.ACTIVE;
    private Long minimumPrice;
    private Long newPrice;

    private Long userId;
}
