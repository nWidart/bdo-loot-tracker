package com.bdoloottracker.price.controller;

import com.bdoloottracker.price.client.item.Item;
import com.bdoloottracker.price.client.item.ItemClient;
import com.bdoloottracker.price.dto.ItemInfo;
import com.bdoloottracker.price.dto.ItemPrice;
import com.bdoloottracker.price.exception.ItemNotFoundException;
import com.bdoloottracker.price.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/price")
public class PriceController {

  private final PriceService priceService;
  private final ItemClient itemClient;

  public PriceController(PriceService priceService, ItemClient itemClient) {
    this.priceService = priceService;
    this.itemClient = itemClient;
  }

  @GetMapping({"{region}/{itemId}", "{region}/{itemId}"})
  public ItemPrice getPrice(@PathVariable String region, @PathVariable String itemId,
      @RequestHeader("Authorization") String jwt) {
    Item item = itemClient.findItemInfo(itemId, jwt);
    if (item == null) {
      throw new ItemNotFoundException();
    }
    if (!item.isTaxable()) {
      return ItemPrice.builder().pricePerOne(item.getPrice().intValue()).build();
    }
    ItemInfo itemInfo = ItemInfo.of(region, item.getBdoDatabaseId().toString());

    return priceService.fetchFor(itemInfo);
  }
}
