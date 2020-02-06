package com.bdoloottracker.run.client;

import com.bdoloottracker.run.dto.ItemPrice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient("price-service")
public interface PriceServiceClient {

  @GetMapping(value = "/api/price/{region}/{itemId}")
  ItemPrice getPriceForItem(@PathVariable("region") String region, @PathVariable("itemId") String itemId,
      @RequestHeader("Authorization") String token);
}
