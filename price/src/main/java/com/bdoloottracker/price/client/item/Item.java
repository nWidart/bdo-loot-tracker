package com.bdoloottracker.price.client.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

  private Long id;
  private String name;
  private boolean isTaxable;
  private Integer bdoDatabaseId;
  private Long price;
}
