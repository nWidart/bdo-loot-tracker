package com.bdoloottracker.item.request;

import com.bdoloottracker.item.entity.Spot;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSpotRequest {

  private String name;

  public Spot toModel() {
    return Spot.builder().name(this.name).build();
  }
}
