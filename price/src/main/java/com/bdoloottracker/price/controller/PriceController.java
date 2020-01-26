package com.bdoloottracker.price.controller;

import com.bdoloottracker.price.dto.ItemInfo;
import com.bdoloottracker.price.dto.ItemPrice;
import com.bdoloottracker.price.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/price")
public class PriceController {

  private final PriceService priceService;

  public PriceController(PriceService priceService) {
    this.priceService = priceService;
  }

  @GetMapping({"{region}/{itemId}", "{region}/{itemId}/{enhanceLevel}"})
  public ItemPrice getPrice(@PathVariable String region, @PathVariable String itemId,
      @PathVariable(required = false) String enhanceLevel) {
    ItemInfo itemInfo = ItemInfo.of(region, itemId, enhanceLevel);

    return priceService.fetchFor(itemInfo);
  }
}
