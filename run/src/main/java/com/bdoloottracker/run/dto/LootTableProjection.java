package com.bdoloottracker.run.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LootTableProjection {

  private Long spotId;
  private List<Item> items = new ArrayList<>();

  @Data
  @AllArgsConstructor
  public static class Item {

    private Long itemId;
    private String name;
  }
}
