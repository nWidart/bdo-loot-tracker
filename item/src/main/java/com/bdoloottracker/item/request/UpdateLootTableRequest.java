package com.bdoloottracker.item.request;

import lombok.Data;

@Data
public class UpdateLootTableRequest {

  private Long itemId;
  private Long spotId;
}
