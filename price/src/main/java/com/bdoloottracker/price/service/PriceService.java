package com.bdoloottracker.price.service;

import com.bdoloottracker.price.client.omegapepega.OmegapepegaClient;
import com.bdoloottracker.price.client.omegapepega.OmegapepegaPrice;
import com.bdoloottracker.price.dto.ItemInfo;
import com.bdoloottracker.price.dto.ItemPrice;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  private final OmegapepegaClient client;

  public PriceService(OmegapepegaClient client) {
    this.client = client;
  }

  public ItemPrice fetchFor(ItemInfo itemInfo) {
    OmegapepegaPrice omegapepegaPrice = client.get(itemInfo);

    return ItemPrice.builder()
        .id(omegapepegaPrice.getId())
        .name(omegapepegaPrice.getName())
        .pricePerOne(omegapepegaPrice.getPricePerOne())
        .count(omegapepegaPrice.getCount())
        .grade(omegapepegaPrice.getGrade())
        .mainCategory(omegapepegaPrice.getMainCategory())
        .subCategory(omegapepegaPrice.getSubCategory())
        .totalTradeCount(omegapepegaPrice.getTotalTradeCount())
        .build();
  }
}
