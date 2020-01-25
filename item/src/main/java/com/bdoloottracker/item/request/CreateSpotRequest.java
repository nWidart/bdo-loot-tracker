package com.bdoloottracker.item.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSpotRequest {

  private String name;
}
