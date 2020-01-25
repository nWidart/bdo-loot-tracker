package com.bdoloottracker.item.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateItemRequest {

  private String name;
  private boolean isTaxable;
}
