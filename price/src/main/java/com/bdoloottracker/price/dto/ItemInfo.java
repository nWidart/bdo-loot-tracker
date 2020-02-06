package com.bdoloottracker.price.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.util.Assert;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value(staticConstructor = "of")
public class ItemInfo {

  private String region;
  private String itemId;
  private String enhancementLevel;

  public static ItemInfo of(String region, String itemId) {
    Assert.notNull(region, "Region is required");
    Assert.notNull(itemId, "Item ID is required");

    return new ItemInfo(region, itemId, null);
  }
}
