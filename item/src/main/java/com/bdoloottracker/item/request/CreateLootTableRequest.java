package com.bdoloottracker.item.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLootTableRequest {

  private Long itemId;
  private Long spotId;
}
