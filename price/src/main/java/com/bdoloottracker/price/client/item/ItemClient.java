package com.bdoloottracker.price.client.item;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient("item-service")
public interface ItemClient {

  @GetMapping("/api/item/items/{itemId}")
  Item findItemInfo(@PathVariable("itemId") String itemId, @RequestHeader("Authorization") String token);
}
