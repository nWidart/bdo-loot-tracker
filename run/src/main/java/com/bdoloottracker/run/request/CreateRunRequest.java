package com.bdoloottracker.run.request;

import java.util.List;
import lombok.Data;

@Data
public class CreateRunRequest {

  private Long sessionId;
  private List<ItemDropMap> itemDrops;
  private String comment;

  @Data
  public static class ItemDropMap {

    private Long itemId;
    private Integer total;
  }
}
