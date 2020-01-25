package com.bdoloottracker.item.request;

import com.bdoloottracker.item.entity.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateItemRequest {

  private String name;
  private boolean isTaxable;

  public Item toModel() {
    return Item.builder().name(this.name).isTaxable(this.isTaxable).build();
  }
}
