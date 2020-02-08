package com.bdoloottracker.run.client;

import com.bdoloottracker.run.dto.LootTableProjection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient("item-service")
public interface ItemServiceClient {

  @GetMapping(value = "/api/loot-table/loot-tables?spotId={spotId}")
  LootTableProjection getLootTableInfoForSpot(@PathVariable String spotId,
      @RequestHeader("Authorization") String token);
}
